package com.gcbjs.metrics;

import java.math.BigDecimal;

/**
  *@ClassName IAccountService
  *@Description 费用接口
  *@Author yuzhangbin
  *@Date 2024/2/5 16:34
  *@Version 1.0
**/
public interface IAccountService {

    /**
    * 提现
    * @param amount
    * @return void
    * @date: 2024/2/5 16:35
    */
    void withdrawOrder(BigDecimal amount);

    /**
     * 充值操作
     */
    void rechargeOrder(BigDecimal amount);

}
