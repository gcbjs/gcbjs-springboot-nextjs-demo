package com.gcbjs.redis.twolevelcache.v3;

import lombok.Data;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.TreeMap;

/**
 * @ClassName SpringElUtil
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/1 22:41
 * @Version 1.0
 **/
public class ElParser {

    public static String parse(String elString, TreeMap<String,Object> map){
        elString=String.format("#{%s}",elString);
        //创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        //通过evaluationContext.setVariable可以在上下文中设定变量。
        EvaluationContext context = new StandardEvaluationContext();
        map.forEach(context::setVariable);

        //解析表达式
        Expression expression = parser.parseExpression(elString, new TemplateParserContext());
        //使用Expression.getValue()获取表达式的值，这里传入了Evaluation上下文
        return expression.getValue(context, String.class);
    }

//    public static void main(String[] args) {
//        String elString="#order.money";
//        String elString2="#user";
//        String elString3="#p0";
//
//        TreeMap<String,Object> map=new TreeMap<>();
//        Order order = new Order();
//        order.setId(111L);
//        order.setMoney(123D);
//        map.put("order",order);
//        map.put("user","Hydra");
//
//        String val = parse(elString, map);
//        String val2 = parse(elString2, map);
//        String val3 = parse(elString3, map);
//
//        System.out.println(val);
//        System.out.println(val2);
//        System.out.println(val3);
//    }
//
//    @Data
//    static class Order {
//        private Long id;
//        private Double money;
//    }
}
