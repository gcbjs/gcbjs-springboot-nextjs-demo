package com.gcbjs.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName MoneyUtils
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 17:03
 * @Version 1.0
 **/
public class MoneyUtils {


    /**
     * 将以分为单位的金额转化为以元为单位的金额，保留两位小数
     *
     * @param amountInCent 以分为单位的金额
     * @return 以元为单位的金额（带两位小数）
     */
    public static String formatAmountInYuan(Long amountInCent) {
        if (amountInCent == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        BigDecimal amountInYuan = BigDecimal.valueOf(amountInCent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return amountInYuan.toString();
    }
}
