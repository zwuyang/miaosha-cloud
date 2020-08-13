package org.spring.cloud.alibaba.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.service.impl.MiaoShaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzw
 * @Classname MiaoShaController
 * @Description TODO
 * @Date 2020/8/12 10:27
 */
@Slf4j
@RestController
public class MiaoShaController {


    @Autowired
    private MiaoShaService miaoShaService;

    @GetMapping("/ms/{goodsId}")
    public boolean ms(@PathVariable("goodsId") long goodsId){
        return miaoShaService.ms(goodsId);
    }

}
