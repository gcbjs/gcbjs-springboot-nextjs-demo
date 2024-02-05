package com.gcbjs.demo.json;

import com.gcbjs.metrics.IAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @ClassName MetricsController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/5 16:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("metrics")
public class MetricsController {

    @Resource
    private IAccountService accountService;

    /**
     * 充值
     */
    @RequestMapping(value = "recharge", method = RequestMethod.GET)
    public void recharge(@RequestParam("amount") BigDecimal amount) {
        accountService.rechargeOrder(amount);
    }

    /**
     * 提现
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public void withdraw(@RequestParam("amount") BigDecimal amount) {
        accountService.withdrawOrder(amount);
    }

}
