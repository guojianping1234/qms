package com.myspringbt.demo.myenum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Color {

    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4),

    Na("NA", 0);

    // 成员变量
    private String name;
    private int index;

    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Color getColorByStr(String color) {
        switch (color.toLowerCase()) {
            case "红色":
                return RED;
            case "绿色":
                return GREEN;
            case "白色":
                return BLANK;
            case "黄色":
                return YELLO;
            default:
                return Na;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //接口方法
    public String getInfo() {
        return this.name;
    }

    //接口方法
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }

    public static void main(String[] args) {
        List<String> strlist = new ArrayList<>();
        strlist.add("绿色");
        strlist.add("黄色");
        strlist.add("白色");

        //多strlistj进行排序
          List<String> colourlist = strlist.stream()
                .map(s -> Color.getColorByStr(s))
                  .sorted(Comparator.comparing(Color::getIndex).reversed()).map(s->s.getInfo()).collect(Collectors.toList());
        System.out.println(colourlist);
    }


}
