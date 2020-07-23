package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.Dishs;
import com.example.demo3.service.IDishsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

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

    @RequestMapping("/dishs")
    public String getGoodsList(Model model){
        EntityWrapper<Dishs> wrapper = new EntityWrapper<>();
        List<Dishs> dishsList = dishsService.selectList(wrapper);

        model.addAttribute("dishs",dishsList);
        model.addAttribute("falt",3);
        return "dishs/list";
    }


}
