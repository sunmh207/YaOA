package com.jitong.test.annotation.example1;

import java.lang.reflect.Method;

public class Test_1 {
	@Test(id = 1, desc = "hello method_1")
	public void method_1() {
	}

	@Test(id = 2)
	public void method_2() {
	}
	
	@Test(id=3,desc="last method")
	public void method_3(){}
	
	public static void main(String[] args){
		 Method[] methods = Test_1.class.getDeclaredMethods();   
		 for(Method method:methods){
			boolean hasAnnotation =  method.isAnnotationPresent(Test.class);
			if(hasAnnotation){
				Test annotation = method.getAnnotation(Test.class);
				System.out.println("Test (method="+method.getName()+", id="+annotation.id()+", desc="+annotation.desc()+")");
			}
		 }
	}
}
