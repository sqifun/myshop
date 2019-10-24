package com.sqi.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sqi.my.shop.commons.persistence.BaseEntity;
import com.sqi.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 20:08
 */

@Data
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "姓名的长度必须介于6-20位之间")
    private String username;

    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码的长度必须介于6-20位之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;

}
