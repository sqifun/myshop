package com.sqi.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * @author sqi
 * @description: TODO
 * @date 2019-08-19 21:52
 */

@Data
public abstract class BaseEntity implements Serializable {

    private Long id;

    private Date created;

    private Date updated;

}
