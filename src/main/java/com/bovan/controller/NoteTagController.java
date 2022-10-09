package com.bovan.controller;

import com.bovan.common.lang.Result;
import com.bovan.entity.TNote;
import com.bovan.entity.TNoteTag;
import com.bovan.mapper.TNoteTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bovan
 * @since 2022-09-18
 */
@RestController
public class NoteTagController {

    @Autowired
    private TNoteTagMapper tNoteTagMapper;

    /**
     * `
     *
     * @return
     */
    @PostMapping("/note/type")
    public Result<TNote> getNoteTag() {
//        QueryWrapper<TNote> queryWrapper = new QueryWrapper<>();
        List<TNoteTag> list = tNoteTagMapper.selectList(null);
        System.out.println(list);
        return Result.success(200, "操作成功", list);
    }
}
