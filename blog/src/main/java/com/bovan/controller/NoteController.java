package com.bovan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bovan.common.lang.Result;
import com.bovan.mapper.NoteMapper;
import com.bovan.mapper.NoteTagMapper;
import com.bovan.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bowen
 * @since 2022-09-11
 */
@RestController
public class NoteController {

    @Autowired
    private INoteService iNoteService;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    NoteTagMapper noteTagMapper;

    /**
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/{currentPage}/{pageSize}")
    public Result testSelectMapsPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<Map<String,Object>> page = new Page<Map<String,Object>>(currentPage,pageSize);
        IPage<Map<String, Object>> mapIPage = noteMapper.selectMapsPage(page, null);    //要查的表对应的mapper
        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);

        System.out.println("当前页号:"+page.getCurrent());
        System.out.println("获取页号:"+page.getPages());
        System.out.println("每页条数："+page.getSize());
        System.out.println("总信息条数:"+page.getTotal());
        return Result.ok().code(200).message("获取成功").data("pagination",page);
    }

}

