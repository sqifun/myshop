package com.sqi.my.shop.web.ui.api;

import com.sqi.my.shop.commons.utils.HttpClientUtils;
import com.sqi.my.shop.commons.utils.MapperUtils;
import com.sqi.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 15:30
 */

public class ContentsApi {

    /**
     * 请求幻灯片
     *
     * @return
     */
    public static List<TbContent> ppt() {
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
        try {
            tbContents = MapperUtils.json2ListByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}


