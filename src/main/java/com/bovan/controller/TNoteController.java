package com.bovan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bovan.common.lang.Result;
import com.bovan.mapper.TNoteMapper;
import com.bovan.mapper.TNoteTagMapper;
import com.bovan.service.TNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bowen
 * @since 2022-09-11
 */
@RestController
public class TNoteController {

    @Autowired
    TNoteService tNoteService;

    @Autowired
    TNoteMapper tNoteMapper;

    @Autowired
    TNoteTagMapper tNoteTagMapper;

    /**
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/{currentPage}/{pageSize}")
    public Result testSelectMapsPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
        IPage<Map<String, Object>> mapIPage = tNoteMapper.selectMapsPage(page, null);    //要查的表对应的mapper
        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);

        System.out.println("当前页号:" + page.getCurrent());
        System.out.println("获取页号:" + page.getPages());
        System.out.println("每页条数：" + page.getSize());
        System.out.println("总信息条数:" + page.getTotal());
        return Result.success(page);
    }

}
