package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.Dishs;
import com.example.demo3.entity.ImgUrl;
import com.example.demo3.service.IDishsService;
import com.example.demo3.util.DishServeCommon;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Controller
@RequestMapping("/DishsController")
public class DishsController {
    @Autowired
    private IDishsService dishsService;

    /**
     * 进入菜品管理界面
     * @param model
     * @return
     */
    @RequestMapping("/dishs")
    public String getGoodsList(Model model){
        EntityWrapper<Dishs> wrapper = new EntityWrapper<>();
        List<Dishs> dishsList = dishsService.selectList(wrapper);
        model.addAttribute("dishs",dishsList);
        model.addAttribute("falt",3);
        return "dishs/list";
    }

    /**
     * 进入添加菜品页面
     * @param model
     * @return
     */
    @RequestMapping("/uploadDishs")
    public String uploadDishs(Model model){
        model.addAttribute("dishfalt",12);
        return "dishs/dishs_upload";
    }

    /**
     * 进入菜品展示主页面
     * @param model
     * @return
     */
    @RequestMapping("/mainDishs")
    public String mainDishs(Model model){
        model.addAttribute("dishfalt",11);
        return "dishs/list";
    }

    /**
     * 保存菜品
     * @param dishs
     * @return
     */
    @RequestMapping("/saveDishInfo")
    public String saveDishsInfo(Dishs dishs){
        System.out.println(dishs);
        System.out.println(dishs.getIsRecommend());
        dishsService.insert(dishs);
        return "dishs/dishs_upload";
    }

    /**
     * 进入修改菜品的界面
     * @param dishId
     * @param model
     * @return
     */
    @RequestMapping("/editDishInfo/{id}")
    public String editDishInfo(@PathVariable("id") int dishId,
                              Model model){
        Dishs dishs = dishsService.selectById(dishId);
        model.addAttribute("dishs",dishs);
        return "dishs/NewPageDishs";
    }

    /**
     * 上传菜品信息
     * @param image
     * @return
     */
    @RequestMapping("/uploadDishsImg")
    public  @ResponseBody ImgUrl  uploadImg(@RequestParam(required=false )MultipartFile image) {
        System.out.println(image.getContentType());
        System.out.println("image");
        DateFormat df = new SimpleDateFormat("yyyMMddHHmsss");
        String format = df.format(new Date());
        format = format + new Random().nextLong();
        String url = DishServeCommon.DISH_SERVER_HOST + format + ".jpg";//要将图片保存到网络上的地址
        //创建Jersey框架的客户端


        Client client = new Client();
        WebResource resource = client.resource(url);
        try {
            byte[] buf;
            buf = image.getBytes();
            resource.put(String.class, buf);//通过put方式向网络ur1发送资源
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建VO对象，以json的方式返回给前端
        ImgUrl imgUrl = new ImgUrl();
        imgUrl.setUrl(url);
        System.out.println(imgUrl);
        return imgUrl;//以json形 式返回ImgUr1对象
    }

    /**
     * 从数据库修改菜品信息
     * @return
     */
    @PostMapping("/updataDishs")
    public String updataDishs(Dishs dishs) {
        dishsService.update(dishs,new EntityWrapper<Dishs>().eq("dishId",dishs.getDishId()));
        //dishsService.updataDishInfo(dishs);
        return "redirect:/DishsController/dishs";
    }

    /**
     * 删除菜品
     * @param
     * @return
     */
    @RequestMapping("/deleteDishs/{id}")
    public String editDishInfo(@PathVariable("id") int dishId
                               ){
        dishsService.deleteById(dishId);
        return "redirect:/DishsController/dishs";
    }

    /**
     * 搜索菜品
     * @param dishId
     * @param model
     * @return
     */
    @RequestMapping("/selectDishs")
    public String selectDishs(@RequestParam("dishsName") Integer dishsName,Model model){
        Dishs dishs = dishsService.selectById(dishsName);
        model.addAttribute("dishs",dishs);
        return "dishs/list";
    }






    }
