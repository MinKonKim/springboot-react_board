package com.minkon.board.controller;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.entity.BoardEntity;
import com.minkon.board.entity.PopularSearchEntity;
import com.minkon.board.security.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;
    @GetMapping("/top3")
    public ResponseDto<List<BoardEntity>> getTop3(){
        return  boardService.getTop3();
    }

    @GetMapping("/list")
    public ResponseDto<List<BoardEntity>> getList(){
        return boardService.getList();
    }

    @GetMapping("/popularsearchList")
    public ResponseDto<List<PopularSearchEntity>> getPopularsearchList(){
        return boardService.getPopularsearchList();
    }
}
