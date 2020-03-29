package com.myspringbt.demo.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
@RestController
@RequestMapping("v1/import")
public class DataImportController {

    @PostMapping(value = "/uploadFile", consumes = "multipart/*", headers = "content-type=multipart/form-data")

    public String upload(@ApiParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            File uploadfile = new File("tmp/data");
            if (!uploadfile.getParentFile().exists()) {
                uploadfile.getParentFile().mkdirs();
            }
            file.transferTo(new File("tmp/data"));
            return "成功";
        } else {
            return "失败";
        }



    }

    /**
     * 多文件上传
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadFiles", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String uploadFiles(@ApiParam("file") MultipartHttpServletRequest request) throws IOException {
        File savePath = new File(request.getSession().getServletContext().getRealPath("/upload/"));
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    File saveFile = new File(savePath, file.getOriginalFilename());
                    stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    if (stream != null) {
                        stream.close();
                        stream = null;
                    }
                    return "第 " + i + " 个文件上传有错误" + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件为空";
            }
        }
        return "所有文件上传成功";
    }


}
