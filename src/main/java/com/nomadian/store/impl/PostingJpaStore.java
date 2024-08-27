package com.nomadian.store.impl;

import com.nomadian.service.domain.club.Posting;
import com.nomadian.store.PostingStore;
import com.nomadian.store.entity.PostingEntity;
import com.nomadian.store.repository.PostingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostingJpaStore implements PostingStore {

    private PostingRepository postingRepository;

    public PostingJpaStore(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    @Override
    public String create(Posting posting) {
        postingRepository.save(posting.toEntity());

        return posting.getId();
    }

    @Override
    public Posting retrieve(String postingId) {
        Optional<PostingEntity> postingJpo = postingRepository.findById(postingId);

        return postingJpo.get().toDomain();
    }

    @Override
    public List<Posting> retrieveByBoardId(String boardId) {
        List<PostingEntity> postingEntities = postingRepository.findByBoardId(boardId);

        return postingEntities.stream().map(PostingEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Posting> retrieveByTitle(String title) {
        List<PostingEntity> postingEntities = postingRepository.findByTitle(title);

        return postingEntities.stream().map(PostingEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Posting posting) {
        postingRepository.save(posting.toEntity());
    }

    @Override
    public void delete(String posingId) {
        postingRepository.deleteById(posingId);
    }

    @Override
    public boolean exists(String postingId) {
        return postingRepository.existsById(postingId);
    }
}
