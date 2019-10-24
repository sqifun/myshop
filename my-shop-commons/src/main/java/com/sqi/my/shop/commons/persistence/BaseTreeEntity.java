package com.sqi.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-25 20:20
 */

@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {

    private T parent;
    private Boolean isParent;

}
