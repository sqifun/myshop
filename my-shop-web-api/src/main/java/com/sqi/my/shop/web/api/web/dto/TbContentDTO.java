package com.sqi.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容数据传输对象
 * @author sqi
 * @description: TODO
 * @date 2019-08-29 22:23
 */
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
