package com.jitong.test;

import javax.servlet.http.Cookie;

public class CookieTest {
	public static void main(String[] args){
		Cookie cookie = new Cookie("","");
		cookie.setMaxAge(60);
		cookie.setPath("/test/test2");
		cookie.setDomain(".avaya.com");
		//response.addCookie(cookie);
	}
}
