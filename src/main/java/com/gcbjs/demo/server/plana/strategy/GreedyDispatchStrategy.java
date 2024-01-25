package com.gcbjs.demo.server.plana.strategy;

import com.gcbjs.demo.mappers.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @ClassName GreedyDispatchStrategy
 * @Description 贪心算法分配员工
 * @Author yuzhangbin
 * @Date 2024/1/24 18:37
 * @Version 1.0
 **/
@Slf4j
@Service
public class GreedyDispatchStrategy implements DispatchStrategy {
    @Override
    public UserInfo dispatch(DispatchStrategyCmd cmd) {

        UserInfo bestUserInfo = null;
        //最小可能值，比任何实际的负数都要小
        BigDecimal maxScore = new BigDecimal("0.0");
        List<UserInfo> users = cmd.getUsers();
        for (UserInfo user : users) {
            BigDecimal score = calculateScore(user, cmd);
            log.info("用户【{}】得分【{}】", user.getUserId(), score.toString());
            if (score.compareTo(maxScore) > 0) {
                maxScore = score;
                bestUserInfo = user;
            }
        }
        //获取最优员工
        return bestUserInfo;
    }

    /**
     * 计算分数
     *
     * @param user
     * @param cmd
     * @return double
     * @date: 2024/1/25 10:13
     */
    private BigDecimal calculateScore(UserInfo user, DispatchStrategyCmd cmd) {
        BigDecimal score = BigDecimal.ZERO;
        Long loanAmount = cmd.getLoanAmount();

        if (loanAmount <= 50000L) {
            score = score.add(BigDecimal.valueOf(0.2));
        } else if (loanAmount <= 150000L) {
            score = score.add(BigDecimal.valueOf(0.4));
        } else if (loanAmount <= 300000L) {
            score = score.add(BigDecimal.valueOf(0.6));
        } else {
            score = score.add(BigDecimal.valueOf(0.8));
        }

        switch (cmd.getUserLevel()) {
            case HIGH_RISK:
                score = score.add(BigDecimal.valueOf(0.4));
                break;
            case NORMAL:
                // 不需要额外操作
                break;
            case EXCELLENT:
                score = score.subtract(BigDecimal.valueOf(0.4));
                break;
            default:
                break;
        }

        switch (cmd.getDealerType()) {
            case NORMAL:
                // 不需要额外操作
                break;
            case EXCELLENT:
                score = score.subtract(BigDecimal.valueOf(0.8));
                break;
            case HIGH_RISK:
                score = score.add(BigDecimal.valueOf(0.8));
                break;
            default:
                break;
        }

        switch (cmd.getUrgentLevel()) {
            case URGENT:
                score = score.add(BigDecimal.valueOf(0.2));
                break;
            case NORMAL:
                // 不需要额外操作
                break;
            default:
                break;
        }

        switch (user.getAbilityLevel()) {
            case HIGH:
                score = score.add(BigDecimal.valueOf(0.3));
                break;
            case LOW:
                score = score.add(BigDecimal.valueOf(0.1));
                break;
            case NEW:
                score = score.add(BigDecimal.valueOf(0.2));
                break;
            default:
                break;
        }

        // 设置小数位数为2，并使用ROUND_HALF_UP模式进行四舍五入
        score = score.setScale(2, RoundingMode.HALF_UP);
        return score;
    }


}
