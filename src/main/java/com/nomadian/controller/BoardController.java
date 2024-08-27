package com.nomadian.controller;

import com.nomadian.service.domain.club.Board;
import com.nomadian.service.BoardService;
import com.nomadian.controller.dto.BoardDto;
import com.nomadian.shared.NameValueList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public String register(@RequestBody @Valid BoardDto board, @RequestParam String clubName) {
        return boardService.register(board, clubName);
    }

    @GetMapping
    public List<Board> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{boardId}")
    public Board find(@PathVariable String boardId) {
        return boardService.find(boardId);
    }

    @GetMapping
    public List<Board> findByName(@RequestParam String boardName) {
        return boardService.findByName(boardName);
    }

    @GetMapping("/club/{clubName}")
    public List<Board> findByClubName(@PathVariable String clubName) {
        return boardService.findByClubName(clubName);
    }

    @PutMapping("/{boardId}")
    public void modify(@PathVariable String boardId, @RequestBody NameValueList nameValueList) {
        boardService.modify(boardId, nameValueList);
    }

    @DeleteMapping("/{boardId}")
    public void remove(@PathVariable String boardId){
        boardService.remove(boardId);
    }
}
