package com.simpletour.securitydemo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/25
 */
public class ReflectionUtils {

    private static final String BLANK_SPACE = " ";
    private static final String BLANK = "";
    private static final String LEFT_PARENTHESES = "(";
    private static final String RIGHT_PARENTHESES = ")";
    private static final String COMMA = ",";
    private static final String DOT = ".";
    private static final String COMMA_BLANK_SPACE = COMMA + BLANK_SPACE;


    public static String getMethodIdentifier(Method method) {
        if (Objects.isNull(method)) {
            return BLANK;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(method.getDeclaringClass().getName());
        builder.append(DOT);
        builder.append(Modifier.toString(method.getModifiers()));
        builder.append(BLANK_SPACE);
        builder.append(method.getReturnType().getName());
        builder.append(BLANK_SPACE);
        builder.append(method.getName());
        builder.append(LEFT_PARENTHESES);
        builder.append(Arrays.stream(method.getParameterTypes()).map(Class::getName).collect(Collectors.joining(COMMA_BLANK_SPACE)));
        builder.append(RIGHT_PARENTHESES);
        return builder.toString();
    }
}
