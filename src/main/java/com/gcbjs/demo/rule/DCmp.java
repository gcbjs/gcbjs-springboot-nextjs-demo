package com.gcbjs.demo.rule;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @ClassName DCmp
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/30 14:26
 * @Version 1.0
 **/
@Component("d")
public class DCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("d");
    }
}
