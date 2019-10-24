package com.sqi.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 13:56
 */

@Data
public class TbContent implements Serializable {
    private Long id;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private String content;


}
