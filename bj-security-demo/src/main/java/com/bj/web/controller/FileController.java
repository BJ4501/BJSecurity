package com.bj.web.controller;

import com.bj.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by neko on 2018/3/5.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    //文件路径
    private String folder = "C:\\BJDocument\\Code2017.11\\BJSecurity\\aaa";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());//参数名
        System.out.println(file.getOriginalFilename());//文件原名
        System.out.println(file.getSize());

        File localFile = new File(folder,new Date().getTime() + ".txt");
        //将收到的这个文件写入到这个位置
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream()){

            System.out.println(id);

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }


}
