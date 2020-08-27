package com.course.server.service;

import com.course.server.mapper.TestMapper;
import com.course.server.pojo.Test;
import com.course.server.pojo.TestExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    };

}