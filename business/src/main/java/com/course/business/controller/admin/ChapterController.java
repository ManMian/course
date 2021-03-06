package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/chapter/")
public class ChapterController {

    @Resource
    public ChapterService chapterService;

    @PostMapping(value = "list", produces = {"application/json;charset=UTF-8"})
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @PostMapping(value = "save", produces = {"application/json;charset=UTF-8"})
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

}
