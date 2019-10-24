package com.sqi.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员数据传输对象
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 19:20
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
