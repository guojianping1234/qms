package com.myspringbt.demo.model;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVUtils2 {

    /**
     * 生成为CVS文件
     *
     * @param exportData 源数据List
     * @param
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @return
     */
    public static File createCSVFile(List<CouponCountBean> exportData, LinkedHashMap<String, String> titleMap,
                                     String outPutPath, String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }
            // 定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            System.out.println("csvFile：" + csvFile);
            // UTF-8使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),
                    1024);
            System.out.println("csvFileOutputStream：" + csvFileOutputStream);

            // 写入title行数据
            for (Iterator propertyIterator = titleMap.entrySet().iterator(); propertyIterator.hasNext(); ) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null
                        ? new String(((String) propertyEntry.getValue()).getBytes("UTF-8"), "UTF-8") : "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
                System.out.println(new String(((String) propertyEntry.getValue()).getBytes("UTF-8"), "UTF-8"));
            }

            // 头和体中间的换行
            csvFileOutputStream.write("\r\n");


            long i = 0;
            for (CouponCountBean couponCountBean : exportData) {
                csvFileOutputStream.write(couponCountBean.getPhone());
                csvFileOutputStream.write(",");
                csvFileOutputStream.write(couponCountBean.getCoupon_count());
                csvFileOutputStream.write(",");
                csvFileOutputStream.write(couponCountBean.getCoupon_money());
                csvFileOutputStream.write("\r\n");
                // 每1000行提交一次
                if (++i % 1000 == 0) {
                    csvFileOutputStream.flush();
                    System.out.println("this i = " + i);
                }
            }

            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 下载文件
     *
     * @param response
     * @param csvFilePath 文件路径
     * @param fileName    文件名称
     * @throws IOException
     */
    public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)
            throws IOException {
        response.setContentType("application/csv;charset=GBK");
        response.setHeader("Content-Disposition",
                "attachment;  filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
        // URLEncoder.encode(fileName, "GBK")

        InputStream in = null;
        try {
            in = new FileInputStream(csvFilePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            response.setCharacterEncoding("GBK");
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                // out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF
                // });
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 删除该目录filePath下的所有文件
     *
     * @param filePath 文件目录路径
     */
    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    files[i].delete();
                }
            }
        }
    }


    /**
     * 删除单个文件
     *
     * @param filePath 文件目录路径
     * @param fileName 文件名称
     */
    public static void deleteFile(String filePath, String fileName) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    if (files[i].getName().equals(fileName)) {
                        files[i].delete();
                        return;
                    }
                }
            }
        }
    }


    /**
     * 测试数据
     *
     * @param args
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        // 标题
        LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
        titleMap.put("1", "phone");
        titleMap.put("2", "coupon_count");
        titleMap.put("3", "coupon_amount");
        List<CouponCountBean> exportData = new ArrayList<CouponCountBean>();
        for (int i = 0; i < 10; i++) {
            CouponCountBean bean = new CouponCountBean();
            bean.setAccount_id(i + "");
            bean.setCoupon_count(i + 10 + "");
            bean.setCoupon_money(i + 100 + "");
            bean.setPhone(i + 1000 + "");
            exportData.add(bean);
        }
        String path = "D:/export/";
        String fileName = "basic";
        File file = CSVUtils2.createCSVFile(exportData, titleMap, path, fileName);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
        File srcfile1 = new File(path + fileName2);
        fileName = "planning";
        File file2 = CSVUtils2.createCSVFile(exportData, titleMap, path, fileName);
        String file2Name = file2.getName();
        File srcfile2 = new File(path + file2Name);

        File destfile = new File("D:/zip/firest.zip");
        File[] f = new File[2];
        f[0] = srcfile1;
        f[1] = srcfile2;
        zipFiles(f, destfile);


    }


    public static void zipFiles(File[] srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.length; i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles[i]);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static class CouponCountBean {
        public CouponCountBean() {
        }

        private String account_id;
        private String phone;
        private String coupon_count;
        private String coupon_money;

        public String getPhone() {
            return phone;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(String coupon_count) {
            this.coupon_count = coupon_count;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }
    }
}
