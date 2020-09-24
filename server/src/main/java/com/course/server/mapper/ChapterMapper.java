package com.course.server.mapper;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChapterMapper {
    long countByExample(ChapterExample example);

    int deleteByExample(ChapterExample example);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    List<Chapter> selectByExample(ChapterExample example);

    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);
}