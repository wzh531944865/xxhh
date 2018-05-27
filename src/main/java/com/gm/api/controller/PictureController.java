package com.gm.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.gm.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/image")
    public void getImage(@RequestParam(value = "imgName", defaultValue = "wangzhonghao.png") String imgName,
                         HttpServletResponse response) throws Exception {
        String JPG = "image/jpeg;charset=GB2312";
        String nowPath = PictureController.class.getClassLoader().getResource("").getPath();
        System.out.println(nowPath);
        // 本地文件路径
        String filePath = nowPath + imgName;
        File file = new File(filePath);
        // 获取输出流
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        // 读数据
        byte[] data = new byte[fileInputStream.available()];
        fileInputStream.read(data);
        fileInputStream.close();
        // 回写
        response.setContentType(JPG);
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }
}
