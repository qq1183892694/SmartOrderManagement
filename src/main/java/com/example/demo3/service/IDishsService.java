package com.example.demo3.service;

import com.example.demo3.entity.Dishs;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
public interface IDishsService extends IService<Dishs> {

    /**
     * 上传菜品信息，包含图片
     * @param dishs
     */
    public void uploadDishInfo(Dishs dishs);

    /**
     * 更新图片，因为使用了Mybatisplus,所以基本的增删改查就没有写，不知道是不是冲突，我写的几个sql都执行不了
     * @param dishs
     */
    public void updataDishInfo(Dishs dishs);

}
