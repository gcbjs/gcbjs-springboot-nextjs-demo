package com.gcbjs.demo.rule;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @ClassName CCmp
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/30 13:55
 * @Version 1.0
 **/
@Component("c")
public class CCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("c");
    }
}
