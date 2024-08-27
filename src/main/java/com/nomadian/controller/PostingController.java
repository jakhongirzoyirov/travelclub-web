package com.nomadian.controller;

import com.nomadian.service.domain.club.Posting;
import com.nomadian.service.PostingService;
import com.nomadian.controller.dto.PostingDto;
import com.nomadian.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postings")
public class PostingController {
    private PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @PostMapping
    public String register(@RequestBody String boardId, PostingDto posting) {
        return postingService.register(boardId, posting);
    }

    @GetMapping("/{postingId}")
    public Posting find(@PathVariable String postingId) {
        return postingService.find(postingId);
    }

    @GetMapping("/board/{boardId}")
    public List<Posting> findByBoardId(@PathVariable String boardId) {
        return postingService.findByBoardId(boardId);
    }

    @PutMapping("/{postingId}")
    public void modify(@PathVariable String postingId, @RequestBody NameValueList nameValueList) {
        postingService.modify(postingId, nameValueList);
    }

    @DeleteMapping("/{postingId}")
    public void remove(@PathVariable String postingId) {
        postingService.remove(postingId);
    }

    @DeleteMapping("/board/{boardId}")
    public void removeAllIn(@PathVariable String boardId) {
        postingService.removeAllIn(boardId);
    }
}
