package com.xgf.service.system.dept;


public class Test {
    String str = "good";
    char[] ch = {'a','b','c'};
    public void change(String str, char[] ch) {
        str = "test ok";
        ch[0] = 'g';
    }
    public static void main(String args[]) {
        Test ex = new Test();
        ex.change(ex.str,ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }
}
