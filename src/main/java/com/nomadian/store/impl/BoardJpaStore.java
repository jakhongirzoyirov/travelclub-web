package com.nomadian.store.impl;

import com.nomadian.service.domain.club.Board;
import com.nomadian.store.BoardStore;
import com.nomadian.store.entity.BoardEntity;
import com.nomadian.store.repository.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BoardJpaStore implements BoardStore {

    private BoardRepository boardRepository;

    public BoardJpaStore(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String create(Board board) {
        boardRepository.save(board.toEntity());

        return board.getId();
    }

    @Override
    public Board retrieve(String boardId) {
        Optional<BoardEntity> boardJpo = boardRepository.findById(boardId);

        return boardJpo.get().toDomain();
    }

    @Override
    public List<Board> retrieveAll() {
        List<BoardEntity> boardEntities = boardRepository.findAll();

        return boardEntities.stream().map(BoardEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Board> retrieveByName(String name) {
        List<BoardEntity> boardEntities = boardRepository.findByName(name);

        return boardEntities.stream().map(BoardEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Board> retrieveByClubName(String name) {
        List<BoardEntity> boardEntities = boardRepository.findByClubName(name);

        return boardEntities.stream().map(BoardEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Board board) {
        boardRepository.save(board.toEntity());
    }

    @Override
    public void delete(String boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public boolean exists(String boardId) {
        return boardRepository.existsById(boardId);
    }
}
