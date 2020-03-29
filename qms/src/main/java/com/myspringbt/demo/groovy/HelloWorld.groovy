package com.myspringbt.demo.groovy

class HelloWorld {
    static void main(String[] args) {
        //基本语法
        //println("Hello World");
        // def x=5;
        //println(x);
        //基本数据类型  =java
        //变量
        //循环
/*        int[] array = [0, 1, 2, 3];
        //for in
        for (int i in array) {
            println i;
        }*/

        firstName()
        secondName("d", "f")
    }

    //方法
    static def firstName() {
        println "Hello firstName"
    }
    //第二个方法
    static void secondName(String... a) {
        a.each {
            print it;
        }
    }


}
