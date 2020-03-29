package com.myspringbt.demo.service;


import com.myspringbt.demo.dao.CommonDao;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    @Autowired
    CommonDao commonDao;

    public List<Map<String, Object>> findAllMessage() {
        return commonDao.findAllMessage();
    }

    /**
     * 对excel 进行操作
     *
     * @param url
     * @return
     */
    public String modifyExcel(String url) {
        try {
            String fileName = url;
            XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(fileName));
            String fillStr = ""; // 存储aaa文件里的数据
            XSSFSheet xSheet = xwb.getSheetAt(0); // 获取excel表的第一个sheet
            for (int i = 1; i <= xSheet.getLastRowNum(); i++) { // 遍历所有的行
                if (xSheet.getRow(i) == null) { // 这行为空执行下次循环
                    continue;
                }
                // 数据从第二行开始
                for (int j = 0; j <= xSheet.getRow(i).getPhysicalNumberOfCells(); j++) { // 遍历当前行的所有列
                    if (xSheet.getRow(i).getCell(j) == null) {// 为空执行下次循环
                        continue;
                    }
                    fillStr = (xSheet.getRow(i)).getCell(j).toString();// 获取当前单元格的数据
                    XSSFCell xCell = xSheet.getRow(i).getCell(j); // 获取单元格对象，这块不能向上边那两句代码那么写，不能用createXXX，用的话会只把第一列的数据改掉
                    // 修改数据,只修改第一 第二 第三列
                    switch (j) {
                        case 0:
                            xCell.setCellValue(fillStr = "修改1");
                            break;
                        case 1:
                            xCell.setCellValue(fillStr = "修改2");
                            break;
                        case 2:
                            xCell.setCellValue(fillStr = "修改3");
                            break;
                        default:
                            xCell.setCellValue(fillStr);
                            break;
                    }

                }
            }
            FileOutputStream out = new FileOutputStream(fileName);
            xwb.write(out);
            out.close();
            xwb.close();
            return "修改成功";
        } catch (Exception e) {
            // TODO: handle exception
            return "修改失败";
        }

    }

    /**
     * 下载模板
     *
     * @param response
     * @param session
     * @param name
     * @return
     */
    public void downExcel(HttpServletResponse response, HttpSession session, String name) {
        try {
            InputStream inputStream = (InputStream) this.getClass().getClassLoader()
                    .getResourceAsStream(name + ".xlsx");
            response.setContentType("application/zip");
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=" + name + ".xlsx");
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if (b != -1)
                    out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * 加强版本下载模板，修改其模板固定相内容
     *
     * @param req
     * @param res
     * @param session
     * @param name
     */
    public void downWhatExcel(HttpServletRequest req, HttpServletResponse res, HttpSession session, String name) {
        try {
            // 1.下载文件只临时文件夹
            InputStream inputStream = (InputStream) this.getClass().getClassLoader()
                    .getResourceAsStream(name + ".xlsx");
            // 临时文件夹
            String strs = "C:\\Users\\Administrator\\Desktop\\temp" + File.separator + name + ".xlsx";
            FileOutputStream fos = null;
            fos = new FileOutputStream(strs);
            InputStream ins = null;
            OutputStream out = null;
            byte[] buf = new byte[1024 * 1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
                // 2.讲内容写入临时文件
                modifyExcel(strs);

                // 3. 对最新文件下载
                ins = new FileInputStream(new File(strs));
                res.setContentType("application/zip");
                out = res.getOutputStream();
                res.setHeader("Content-Disposition", "attachment; filename=" + name + ".xlsx");
                int b = 0;
                byte[] buffer = new byte[1024 * 1024];
                while (b != -1) {
                    b = ins.read(buffer);
                    if (b != -1)
                        out.write(buffer, 0, b);
                }
            }
            fos.close();
            ins.close();
            inputStream.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }

    }

    public void copy(String[] args) {
        /* 指定源exe文件的存放路径 */
        String str = "f:/jdk-1_5_0_06-windows-i586-p.exe";
        /* 指定复制后的exe的目标路径 */
        String strs = "e:/copy.exe";
        /* 创建输入和输出流 */
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            /* 将io流和文件关联 */
            fis = new FileInputStream(str);

            fos = new FileOutputStream(strs);
            byte[] buf = new byte[1024 * 1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private String getValue(XSSFCell xCell) {
        if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {

            return String.valueOf(xCell.getBooleanCellValue());
        } else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

            return String.valueOf(xCell.getNumericCellValue());
        } else {

            return String.valueOf(xCell.getStringCellValue());
        }

    }
}
