package com.myspringbt.demo.model;

public class Test {
    static int x = 1;
    int y;

    Test() {
        y++;
    }
    public static void main(String args[]){
        Test t=new Test();
        System.out.println(x);
        System.out.println(t.y);
        t=new Test();
        System.out.println(t.y);
    }
    static {
        x++;
    }
}
