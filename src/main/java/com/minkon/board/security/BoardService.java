package com.minkon.board.security;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.entity.BoardEntity;
import com.minkon.board.entity.PopularSearchEntity;
import com.minkon.board.repository.BoardRepository;
import com.minkon.board.repository.PopularSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PopularSearchRepository popularSearchRepository;

    public ResponseDto<List<BoardEntity>> getTop3(){

        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        // 오늘 날짜 기준 7일 이전
        Date date = Date.from(Instant.now().now().minus(7, ChronoUnit.DAYS));

        try{
            boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
        }catch(Exception exception){
            return ResponseDto.setFailed("DataBase Error");
        }
        return ResponseDto.setSuccess("Success",boardList);
    }

    public ResponseDto<List<BoardEntity>> getList(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try{
            boardList = boardRepository.findByOrderByBoardWriteDateDesc();
        }catch (Exception exception){
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success",boardList);
    }

    public ResponseDto<List<PopularSearchEntity>> getPopularsearchList(){

        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();

        try{
            popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
            return ResponseDto. setSuccess("Success", popularSearchList);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

    }

}
