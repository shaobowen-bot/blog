package com.bovan;

import com.bovan.entity.TNote;
import com.bovan.entity.TUser;
import com.bovan.mapper.TNoteMapper;
import com.bovan.mapper.TUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private TNoteMapper tNoteMapper;

    @Autowired
    private TUserMapper tUserMapper;

    @Test
    public void selectListTest() {
        List<TNote> list = tNoteMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void selectUserListTest() {
        List<TUser> list = tUserMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
