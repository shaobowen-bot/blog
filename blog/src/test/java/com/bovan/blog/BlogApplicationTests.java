package com.bovan.blog;

import com.bovan.domain.entity.Permission;
import com.bovan.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }
    @Test
    void test(){
        List<String> list = userMapper.selectPermsByUserName("bovan");
        System.out.println(list);

    }

}
