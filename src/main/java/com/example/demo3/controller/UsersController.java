package com.example.demo3.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo3.config.Fileupload;
import com.example.demo3.entity.Users;
import com.example.demo3.entity.vo.UsersAuthorityVO;
import com.example.demo3.service.IUsersService;
import com.example.demo3.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Controller
@RequestMapping("/UsersController")
public class UsersController {

    @Autowired
    IUsersService usersService;
    @Autowired
    Fileupload fileupload;

    @GetMapping("/users")
    public String list(Model model)
    {


        List<UsersAuthorityVO> usersList=usersService.findUsersAuthority();
        model.addAttribute("users",usersList);
        model.addAttribute("falt",1);
        return "user/list";
    }
    @GetMapping("/addView")
    public String addView(Model model)
    {
        model.addAttribute("falt",1);
        return "user/add";
    }
    @GetMapping("/updateView/{id}")
    public String addView(@PathVariable("id")Integer id,Model model)
    {
        model.addAttribute("falt",1);
        Users user=usersService.selectById(id);
        model.addAttribute("user",user);
        return "user/edit";
    }


    @PostMapping("/checkuser")
    public @ResponseBody Map<String,Object> checkuser(@RequestBody Map<String,String> value)
    {
        Map<String,Object> rt=new LinkedHashMap<>();
        String username=value.get("username");
        System.out.println(username);
        Users user=usersService.findUserByUserName(username);
        if(user!=null)
        {
            rt.put("msg","用户名已经存在");
            rt.put("success",false);
        }else
        {
            rt.put("msg","");
            rt.put("success",true);
        }
        return rt;
    }

    @GetMapping("/getpic/{path}")
    public @ResponseBody byte[] checkuser(@PathVariable("path") String path)
    {
        String filepath=fileupload.getImgfile()+"/"+path;
        File file=new File(filepath);
        if(!file.exists()){
            return null;
        }
        FileInputStream fileInputStream= null;
        byte[]bytes=null;
        try {
            fileInputStream = new FileInputStream(file);
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes,0,fileInputStream.available());
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return bytes;


    }

    @PostMapping("/reset")
    public @ResponseBody Map<String,Object> reset(@RequestBody Map<String,String> value)
    {
        Map<String,Object> rt=new LinkedHashMap<>();
        Long uid= Long.valueOf(value.get("uid"));
        System.out.println(uid);
        Users user=Users.builder().userId(uid).userPassword(DigestUtils.md5DigestAsHex("123456".getBytes())).build();
        boolean flag=usersService.updateById(user);
        if(!flag)
        {
            rt.put("msg","重置失败");
            rt.put("success",false);
        }else
        {
            rt.put("msg","重置成功");
            rt.put("success",true);
        }
        return rt;
    }

    @PostMapping("/delete")
    public @ResponseBody Map<String,Object> delete(@RequestBody Map<String,String> value)
    {
        Map<String,Object> rt=new LinkedHashMap<>();
        Long uid= Long.valueOf(value.get("uid"));
        System.out.println(uid);
        Users olduser=usersService.selectById(uid);
        int i = usersService.deleteUser(uid);
        if(i!=0)
        {
            String oldfilepath=fileupload.getImgfile()+"/"+olduser.getUserAvatarPath();
            File file=new File(oldfilepath);
            if(file.exists()){
                file.delete();
            }
            rt.put("msg","删除成功");
            rt.put("success",true);
        }else
        {
            rt.put("msg","删除失败");
            rt.put("success",false);
        }
        return rt;
    }

    @PostMapping("/add")
    public String add(@RequestParam("avatarfile") MultipartFile multipartFile,
                                               @RequestParam("username" ) String username,
                                               @RequestParam("password" ) String password,
                                               @RequestParam("authorityid" ) Integer authorityid
                                               )
    {

        System.out.println(username+" "+password+" "+authorityid);
        Users user = Users.builder().userLoginName(username).userPassword(DigestUtils.md5DigestAsHex(password.getBytes())).
                    characterId(authorityid).build();
        if(!multipartFile.isEmpty())
        {
          String dirpath=fileupload.getImgfile();
          File dir=new File(dirpath);
          if(!dir.exists()){
              dir.mkdirs();
          }
            System.out.println(multipartFile.getOriginalFilename());
          String savefilename= FileUtil.renameToUUID(multipartFile.getOriginalFilename());
          user.setUserAvatarPath(savefilename);
          File dest = new File(dir,savefilename);
            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                return "error/404";
            }
        }
        usersService.addUser(user);
        return "redirect:/UsersController/users";

    }

    @PostMapping("/update")
    public String update(@RequestParam("avatarfile") MultipartFile multipartFile,
                         @RequestParam("uid" ) Long uid,
                      @RequestParam("username" ) String username,
                      @RequestParam("authorityid" ) Integer authorityid
    )
    {

        System.out.println(username+" "+authorityid+""+uid);
        Users user = Users.builder().userId(uid).userLoginName(username).characterId(authorityid).build();
        if(!multipartFile.isEmpty())
        {
            Users olduser=usersService.selectById(uid);
            String oldfilepath=fileupload.getImgfile()+"/"+olduser.getUserAvatarPath();
            File file=new File(oldfilepath);
            if(file.exists()){
                file.delete();
            }
            String dirpath=fileupload.getImgfile();
            File dir=new File(dirpath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            System.out.println(multipartFile.getOriginalFilename());
            String savefilename= FileUtil.renameToUUID(multipartFile.getOriginalFilename());
            user.setUserAvatarPath(savefilename);
            File dest = new File(dir,savefilename);
            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                return "error/404";
            }
        }

        usersService.updateById(user);
        return "redirect:/UsersController/users";

    }

}
