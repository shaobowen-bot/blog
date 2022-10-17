package com.bovan.mapper;

import com.bovan.domain.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
