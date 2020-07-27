package com.example.demo3.entity;

import java.util.Objects;

/**
 * Created on 2020/7/25.
 * 用来在控制层和视图层之间传输上传图片的地址
 * @author Zhouyong Tan
 */
public class ImgUrl {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgUrl imgUrl = (ImgUrl) o;
        return Objects.equals(url, imgUrl.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "ImgUrl [url=" + url +  "]";
    }
}
