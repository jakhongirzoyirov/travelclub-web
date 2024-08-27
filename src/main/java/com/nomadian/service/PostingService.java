package com.nomadian.service;


import com.nomadian.service.domain.club.Posting;
import com.nomadian.controller.dto.PostingDto;
import com.nomadian.shared.NameValueList;

import java.util.List;

public interface PostingService {
    String register(String boardId, PostingDto posting);
    Posting find(String postingId);
    List<Posting> findByBoardId(String boardId);
    void modify(String postingId, NameValueList nameValueList);
    void remove(String postingId);
    void removeAllIn(String boardId);
}
