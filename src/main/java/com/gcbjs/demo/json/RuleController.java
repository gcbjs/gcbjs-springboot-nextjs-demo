package com.gcbjs.demo.json;

import com.gcbjs.demo.json.param.RuleContext;
import com.gcbjs.demo.util.Result;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RuleController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/30 14:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Resource
    private FlowExecutor flowExecutor;

    @RequestMapping("/test")
    public Result<Boolean> testConfig() {
        RuleContext context = new RuleContext();
        context.setAge(18);
        context.setName("yzb");
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", null,context);
        return Result.success(response.isSuccess());
    }

}
