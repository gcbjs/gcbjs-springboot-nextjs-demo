package com.gcbjs.demo.json;

import com.gcbjs.demo.json.param.RuleContext;
import com.gcbjs.demo.util.Result;
import com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha.CaptchaAppService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private CaptchaAppService captchaAppService;

    @RequestMapping("/test")
    public Result<Boolean> testConfig() {
        RuleContext context = new RuleContext();
        context.setAge(18);
        context.setName("yzb");
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", null, context);
        return Result.success(response.isSuccess());
    }


    /**
     * 发送验证码
     *
     * @param mobile
     * @return com.gcbjs.demo.util.Result<java.lang.String>
     * @date: 2024/2/4 14:50
     */
    @RequestMapping(value = "/sendCaptchaCode", method = RequestMethod.GET)
    public Result<String> sendCaptchaCode(@RequestParam("mobile") String mobile) {
        return Result.success(captchaAppService.sendCaptchaCode(mobile, "rate_limit_rule"));
    }

}
