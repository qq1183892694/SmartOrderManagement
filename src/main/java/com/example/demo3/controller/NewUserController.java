package com.example.demo3.controller;

import com.example.demo3.entity.Dishs;
import com.example.demo3.entity.ImgUrl;
import com.example.demo3.service.NewUserService;
import com.example.demo3.util.FtpUtils;
import com.example.demo3.util.IDUtils;
import com.example.demo3.util.SftpUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2020/7/26.
 *
 * @author Zhouyong Tan
 */
@Controller
@RequestMapping("/NewUserController")
public class NewUserController {
    @Value("${FTP.ADDRESS}")
    private String host;
    // 端口
    @Value("${FTP.PORT}")
    private int port;
    // ftp用户名
    @Value("${FTP.USERNAME}")
    private String userName;
    // ftp用户密码
    @Value("${FTP.PASSWORD}")
    private String passWord;
    // 文件在服务器端保存的主目录
    @Value("${FTP.BASEPATH}")
    private String basePath;
    // 访问图片时的基础url
    @Value("${IMAGE.BASE.URL}")
    private String baseUrl;

    @Autowired
    private NewUserService userService;
    private FtpUtils FtpUtil;
    String filePath;


    /**
     * 图片上传，返回图片浏览器路径
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")

    public @ResponseBody ImgUrl pictureUpload(
            @RequestParam("pic") MultipartFile uploadFile,
            Model model) {
        try {

            System.out.println("进入");
            System.out.println(uploadFile);

            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            System.out.println(filePath);
            System.out.println(newName);

            //2、把前端输入信息，包括图片的url保存到数据库
//            NewUser user = new NewUser();
//            user.setUsername(username);
//            user.setPassword(password);
//            user.setPicture(baseUrl + filePath + "/" + newName);
//            System.out.println(user);
//            userService.insert(user);

            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = uploadFile.getInputStream();


            //之前使用的FTPTils工具类，但是我部署的浏览器不支持连接，就改成了SFtpUtil
            //3.2调用SFtpUtil工具类进行上传
            boolean result = SftpUtils.uploadBySftp(host,  userName,passWord, port,  basePath, filePath, newName, input);
            System.out.println(result);
            this.filePath = "http://47.98.248.3" + filePath + "/" + newName;
            System.out.println(this.filePath);

            if (result) {
                System.out.println(result);
            } else {

            }
        } catch (IOException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }


        ImgUrl imgUrl = new ImgUrl();
        imgUrl.setUrl(this.filePath);

        return imgUrl;
    }


}
