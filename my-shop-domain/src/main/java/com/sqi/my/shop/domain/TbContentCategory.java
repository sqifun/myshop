package com.sqi.my.shop.domain;

import com.sqi.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 分类管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-20 21:33
 */

@Data
public class TbContentCategory extends BaseTreeEntity {

    @Length(min = 1, max = 20, message = "分类名称必须介于 1 - 20 位之间")
    private String name;

    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    private Boolean isParent;
    private TbContentCategory parent;

}
