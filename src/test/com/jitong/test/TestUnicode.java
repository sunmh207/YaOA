package com.jitong.test;

public class TestUnicode {
	private   String   resolveName(String   name)   { 
            if   (name   ==   null)   { 
                    return   name; 
            } 
            if   (!name.startsWith( "/ "))   { 
                    Class   c   =   this.getClass(); 
                    while   (c.isArray())   { 
                            System.out.println( "is   array. "); 
                            c   =   c.getComponentType(); 
                    } 
                    String   baseName   =   c.getName(); 
                    int   index   =   baseName.lastIndexOf( '.'); 
                    if   (index   !=   -1)   { 
                            name   =   baseName.substring(0,   index).replace( '.',   '/') 
                                    + "/ "+name; 
                    } 
            }   else   { 
                    name   =   name.substring(1); 
            } 
            return   name; 
    }
	public static void main(String[] args) {

		//int len = args[0].length();
		//String[] s = new String[len];

		String hanzi="一二三四五六七八九十百千";
		int len = hanzi.length();
		String[] s = new String[len];
		for (int i = 0; i < len; i++) {
			char c = hanzi.charAt(i);
			s[i] = Integer.toString(c, 16);
			// s[i] = Byte.toString((byte)c);
			System.out.println(c + "\t\\u " + s[i]);
		}

		System.out.println();
		for (int i = 0; i < s.length; i++) {
			char c = (char) Integer.valueOf(s[i], 16).intValue();
			System.out.println("\\u" + s[i] + "\t" + c);
		}

		Object o = new Object() {
			public String toString() {
				// return getClass().resolveName( "/resource ").toString();
				return " ";
			}
		};

		// System.out.println(o.toString());
		System.out.println(new TestUnicode().resolveName("res/btn.gif "));
	}
}
