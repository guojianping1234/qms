package Test

class Resolution {

    public static void main(String[] args) {
        String title;
        StringBuffer sb = new StringBuffer();
        Map<String, String> map = new HashMap<>()
        boolean need = false;
        //无分隔符人为制造分隔
        File outFile = new File("model/acopy.txt");
        //创建新文件
//        if (outFile.createNewFile()) {
//
//        }
        //都文件并写入新文件
        def printWriter = outFile.newWriter()
        new File("model/a.txt").eachLine {
            if (it.trim().contains("(config#)")) {
                printWriter.write(";\r\n" + it)
            } else {
                printWriter.write(it + "\r\n")
            }
        }
        printWriter.write(";")
        printWriter.flush()
        printWriter.close()


        //读取文件
//        new File("model/a.txt").eachLine {
//
//
//            sb.append(it + "\r\n")
//            //读取到config
//            if (it.trim().contains("(config#)")) {
//                title = it.split("(config#)")*.trim()[1]
//                need=true
//            }


    }


}