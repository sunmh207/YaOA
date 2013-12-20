package com.jitong.test.annotation.example1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)     
/*   
 * 定义注解 Test   
 * 注解中含有两个元素 id 和 description   
 * description 元素 有默认值 "no description"   
 */   
public @interface Test {
	public int id();
	public String desc() default "no description";
}
