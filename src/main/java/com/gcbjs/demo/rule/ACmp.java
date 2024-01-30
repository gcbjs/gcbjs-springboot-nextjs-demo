package com.gcbjs.demo.rule;

import com.alibaba.fastjson.JSON;
import com.gcbjs.demo.json.param.RuleContext;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName Acmp
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/30 11:23
 * @Version 1.0
 **/
@Slf4j
@Component("a")
public class ACmp extends NodeComponent {

    @Override
    public void process() {
        RuleContext ruleContext = this.getContextBean(RuleContext.class);
        log.info("ruleContext:{}", JSON.toJSONString(ruleContext));
        System.out.println("a");
    }


//    @Override
//    public boolean isEnd() {
//        return Boolean.TRUE;
//    }


//    @Override
//    public boolean isAccess() {
//        return Boolean.FALSE;
//    }
}
