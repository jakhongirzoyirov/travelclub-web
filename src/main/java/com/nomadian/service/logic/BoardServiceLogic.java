package com.nomadian.service.logic;

import com.nomadian.service.domain.club.Board;
import com.nomadian.service.BoardService;
import com.nomadian.controller.dto.BoardDto;
import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.BoardStore;
import com.nomadian.store.ClubStore;
import com.nomadian.util.exception.BoardDuplicationException;
import com.nomadian.util.exception.NoSuchBoardException;
import com.nomadian.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceLogic implements BoardService {
    private BoardStore boardStore;
    private ClubStore clubStore;

    public BoardServiceLogic(BoardStore boardStore) {
        this.boardStore = boardStore;
    }

    @Override
    public String register(BoardDto board, String clubName) {

        if (boardStore.retrieveByName(board.getName()) != null) {
            throw new BoardDuplicationException("Board already exists for club with name " + board.getName());
        }

        TravelClub travelClub = Optional.ofNullable(clubStore.retrieveByName(clubName))
                .orElseThrow(() -> new NoSuchClubException("No Such club with name " + clubName));

        Board newBoard = new Board(board.getName(), board.getAdminEmail());
        newBoard.setClubId(travelClub.getId());
        return boardStore.create(newBoard);
    }

    @Override
    public List<Board> findAll() {
        List<Board> foundBoards = boardStore.retrieveAll();
        if (foundBoards.isEmpty()) {
            throw new NoSuchBoardException("No such boards");
        }
        return foundBoards;
    }

    @Override
    public Board find(String boardId) {
        Board foundBoard = boardStore.retrieve(boardId);
        if (foundBoard == null) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        return foundBoard;
    }

    @Override
    public List<Board> findByName(String boardName) {
        List<Board> foundBoards = boardStore.retrieveByName(boardName);

        if (foundBoards == null) {
            throw new NoSuchBoardException("No such boards with name --> " + boardName);
        }

        return foundBoards;
    }

    @Override
    public List<Board> findByClubName(String clubName) {
        List<Board> foundBoards = boardStore.retrieveByClubName(clubName);
        if (foundBoards == null) {
            throw new NoSuchBoardException("No such boards with clubNane --> " + clubName);
        }
        return foundBoards;
    }

    @Override
    public void modify(String boardId, NameValueList nameValueList) {
        Board foundBoard = boardStore.retrieve(boardId);
        if (foundBoard == null) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        foundBoard.modifyValues(nameValueList);
        boardStore.update(foundBoard);
    }

    @Override
    public void remove(String boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        boardStore.delete(boardId);
    }
}
