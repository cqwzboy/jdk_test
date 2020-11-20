package com.code.fuqinqin.spring.el;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author fuqinqin3
 * @date 2020-11-17
 * */
public class ExpressionParserTest {
    @Test
    public void test(){
        // 表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello World'.getBytes()");
        byte[] bytes = (byte[]) expression.getValue();
        System.out.println(bytes.length);
    }
}
