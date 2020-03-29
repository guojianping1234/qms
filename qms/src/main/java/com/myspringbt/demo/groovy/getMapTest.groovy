package com.myspringbt.demo.groovy

import jodd.util.CollectionUtil
import org.apache.commons.fileupload.util.LimitedInputStream
import org.apache.commons.lang.StringUtils

class getMapTest {

    static void main(String[] args) {

        String str = new File("C:\\Users\\guoguo\\Desktop\\java学习\\test.txt").text;
        resultToMaps(str)
        List<Map> result = resultToMaps(str)
        println(result.toString())
        println(result.size())
    }

    static List<Map> resultToMap(String echo) {
        echo = echo.replaceAll("\t", " ");
        List<String> list = echo.split("\n");


        int begin = list.findIndexOf { it.startsWith("——————————") }
        if (begin < 0) {
            return []
        }
        list = list[(begin + 1)..<list.size()]
        int end = list.findIndexOf { it.startsWith("——————————") }
        if (end < 0) {
            return []
        }
        list = list[0..<end]
        if (!list) {
            return []
        }
        List<String> titles = list[0].trim().split(" ").findAll { it }

        return list[1..<list.size()].collect {
            String[] valuse = it.trim().split(" ").findAll { it }
            def map = [:]
            titles.eachWithIndex { String title, int i ->
                map[title] = valuse[i]
            }
            return map
        }


    }

    static List<Map> resultToMaps(String echo) {
        echo = echo.replaceAll("\t", " ");
        List<String> list = echo.split("\n");

        int begin = list.findIndexOf { it.startsWith("——————————") }
        if (begin < 0) {
            return []
        }
        list = list[(begin + 1)..<list.size()]
        int end = list.findLastIndexOf { it.startsWith("——————————") }
        if (end < 0) {
            return []
        }
        list = list[0..<end]

        if (!list) {
            return []
        }
        //截取到所有数据+————————
        String titlestr = list[0]
        List<String> titles = titlestr.trim().split(" ").findAll { it }
        return list[1..<list.size()].collect {
            String[] valuse = it.trim().split(" ").findAll { it }
            if (!(it.trim().equals("——————————")) && !(it.equals(titlestr)) && StringUtils.isNotBlank(it.trim())) {
                def map = [:]
                titles.eachWithIndex { String title, int i ->
                    map[title] = valuse[i]
                }
                return map
            }

        }


    }
}



