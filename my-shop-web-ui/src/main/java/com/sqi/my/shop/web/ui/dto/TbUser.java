package com.sqi.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 18:32
 */
@Data
public class TbUser implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String verification;
}
