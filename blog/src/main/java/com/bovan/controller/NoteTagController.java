package com.bovan.controller;

import com.bovan.common.lang.Result;
import com.bovan.domain.entity.NoteTag;
import com.bovan.mapper.NoteTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bovan
 * @since 2022-09-18
 */
@RestController
public class NoteTagController {

    @Autowired
    private NoteTagMapper noteTagMapper;

    /**
     *`
     * @return
     */
    @PostMapping("/note/type")
    public Result getNoteTag(){
//        QueryWrapper<TNote> queryWrapper = new QueryWrapper<>();
        List<NoteTag> list = noteTagMapper.selectList(null);
        System.out.println(list);
        return Result.ok().code(200).message("操作成功").data("list",list);
    }
}