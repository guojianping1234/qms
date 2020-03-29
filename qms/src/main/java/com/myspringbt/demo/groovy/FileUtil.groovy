package com.myspringbt.demo.groovy

class FileUtil {
    static void main(String[] args) {

        new File("E:\\TestGroovyFile\\test01.txt").eachLine {
            line -> println "line l:$line";
        }


/*        def file = new File('E:/')
        println "File? ${file.isFile()}"
        println "Directory? ${fi` le.isDirectory()}"*/

//        def file = new File('E:/Directory')
//        file.mkdir()
//        file.delete();


        //复制文件
//        def src = new File("E:\\TestGroovyFile\\test01.txt")
//        def dst = new File("E:\\TestGroovyFile\\test03.txt")
//        dst << src.text


//        def rootFiles = new File("test").listRoots()
//        rootFiles.each {
//            file -> println file.absolutePath
//        }

        new File("E:\\TestGroovyFile").eachFileRecurse() {
            file -> println file.getAbsolutePath()
        }

        //Groovy 范围
        //1..10 - 包含范围的示例
        //1 .. <10 - 独占范围的示例
        //'a'..'x' - 范围也可以由字符组成
        //10..1 - 范围也可以按降序排列
        //'x'..'a' - 范围也可以由字符组成并按降序排列。

        //Groovy 列表


    }


}
