package com.bovan.service.impl;

import com.bovan.domain.entity.Note;
import com.bovan.mapper.NoteMapper;
import com.bovan.service.INoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

}
