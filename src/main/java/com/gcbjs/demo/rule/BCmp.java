package com.gcbjs.demo.rule;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import org.springframework.stereotype.Component;

/**
 * @ClassName BCmp
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/30 13:50
 * @Version 1.0
 **/
@Component("b")
public class BCmp extends NodeSwitchComponent {

//    @Override
//    public void process() {
//        System.out.println("b");
//    }


    @Override
    public String processSwitch() throws Exception {
        return "c";
    }
}
