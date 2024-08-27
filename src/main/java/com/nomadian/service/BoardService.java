package com.nomadian.service;

import com.nomadian.service.domain.club.Board;
import com.nomadian.controller.dto.BoardDto;
import com.nomadian.shared.NameValueList;

import java.util.List;

public interface BoardService {
    String register(BoardDto board, String s);
    List<Board> findAll();
    Board find(String boardId);
    List<Board> findByName(String boardName);
    List<Board> findByClubName(String clubName);
    void modify(String boardId, NameValueList nameValueList);
    void remove(String boardId);
}
