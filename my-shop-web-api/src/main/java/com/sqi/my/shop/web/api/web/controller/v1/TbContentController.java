package com.sqi.my.shop.web.api.web.controller.v1;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.domain.TbContent;
import com.sqi.my.shop.web.api.service.TbContentService;
import com.sqi.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-29 21:51
 */
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;

        if(id == null) {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 根据类别 ID 查询内容列表
     * @return
     */
    @RequestMapping(value = "ppt", method = RequestMethod.GET)
    public BaseResult findPPT() {
        List<TbContentDTO> tbContentDTOS = null;

        List<TbContent> tbContents = tbContentService.selectByCatagoryId(89L);
        if (tbContents != null && tbContents.size() > 0) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO tbContentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
        }
        return BaseResult.success("成功", tbContentDTOS);
    }
}
