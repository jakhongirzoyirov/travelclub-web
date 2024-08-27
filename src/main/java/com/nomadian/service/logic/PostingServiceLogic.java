package com.nomadian.service.logic;

import com.nomadian.service.domain.club.Board;
import com.nomadian.service.domain.club.Posting;
import com.nomadian.service.PostingService;
import com.nomadian.controller.dto.PostingDto;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.BoardStore;
import com.nomadian.store.PostingStore;
import com.nomadian.util.exception.NoSuchBoardException;
import com.nomadian.util.exception.NoSuchPostingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServiceLogic implements PostingService {
    private PostingStore postingStore;
    private BoardStore boardStore;

    public PostingServiceLogic(PostingStore postingStore, BoardStore boardStore) {
        this.postingStore = postingStore;
        this.boardStore = boardStore;
    }

    @Override
    public String register(String boardId, PostingDto posting) {
        Board board = boardStore.retrieve(boardId);

        if (board == null) throw new NoSuchBoardException("No such Board with boardId --> " + boardId);

        Posting newPosting = new Posting(board, posting.getTitle(), posting.getWriterEmail(), posting.getContents());
        board.setSequence(board.getSequence()+1);
        String postingId = postingStore.create(newPosting);
        return postingId;
    }

    @Override
    public Posting find(String postingId) {
        Posting foundPosting = postingStore.retrieve(postingId);
        if (foundPosting == null) {
            throw new NoSuchPostingException("No such Posting with id --> " + postingId);
        }
        return foundPosting;
    }

    @Override
    public List<Posting> findByBoardId(String boardId) {
        List<Posting> foundPostings = postingStore.retrieveByBoardId(boardId);
        if (foundPostings == null) {
            throw new NoSuchPostingException("No such Posting with boardId --> " + boardId);
        }
        return foundPostings;
    }

    @Override
    public void modify(String postingId, NameValueList nameValueList) {
        Posting foundPosting = postingStore.retrieve(postingId);
        if (foundPosting == null) {
            throw new NoSuchPostingException("No such posting with id --> " + postingId);
        }
        foundPosting.modifyValues(nameValueList);
        postingStore.update(foundPosting);
    }

    @Override
    public void remove(String postingId) {
        if (!postingStore.exists(postingId)) {
            throw new NoSuchPostingException("No such posting with id --> " + postingId);
        }

        postingStore.delete(postingId);
    }

    @Override
    public void removeAllIn(String boardId) {
        List<Posting> postingList = new ArrayList<>(postingStore.retrieveByBoardId(boardId));
        if (postingList.isEmpty()) {
            throw new NoSuchPostingException("No such posting list");
        }
        postingStore.retrieveByBoardId(boardId).stream()
                .forEach(posting -> postingStore.delete(posting.getId()));
    }
}
