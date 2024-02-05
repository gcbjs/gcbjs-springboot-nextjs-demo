package com.gcbjs.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName IAccountServiceImpl
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/5 16:36
 * @Version 1.0
 **/
@Slf4j
@Service
public class AccountServiceImpl implements IAccountService{

    @Resource
    private MeterRegistry registry;

    private Counter rechargeCounter;

    private Counter withdrawCounter;

    private DistributionSummary withdrawDistributionSummary;

    private DistributionSummary rechargeDistributionSummary;

    private BigDecimal balance = new BigDecimal(1000);

    @PostConstruct
    private void init(){
        rechargeCounter = registry.counter("recharge_counter", "currency", "btc");
        withdrawCounter = registry.counter("withdraw_counter", "currency", "btc");
        rechargeDistributionSummary = registry.summary("recharge_amount", "currency", "btc");
        withdrawDistributionSummary = registry.summary("withdraw_amount", "currency", "btc");
        Gauge.builder("balanceGauge", () -> balance)
                .tags("currency", "btc")
                .description("余额")
                .register(registry);
    }

    @Override
    public void withdrawOrder(BigDecimal amount) {
        log.info(" withdrawOrder amount:{}", amount);
        try {
            if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
                throw new Exception("提现金额不足，提现失败");
            }
            //余额减少
            balance = balance.subtract(amount);
            // 提现笔数埋点数据
            withdrawCounter.increment();
            // 提现金额埋点
            withdrawDistributionSummary.record(amount.doubleValue());
        } catch (Exception e) {
            log.info("withdrawOrder error", e);
        } finally {
            log.info("withdrawOrder result:{}", amount);
        }
    }

    @Override
    public void rechargeOrder(BigDecimal amount) {
        log.info("depositOrder amount:{}", amount);
        try {
            //余额增加
            balance = balance.add(amount);
            //充值笔数埋点
            rechargeCounter.increment();
            //充值金额埋点
            rechargeDistributionSummary.record(amount.doubleValue());

        } catch (Exception e) {
            log.info("rechargeOrder error", e);
        } finally {
            log.info("rechargeOrder result:{}", amount);
        }
    }
}
