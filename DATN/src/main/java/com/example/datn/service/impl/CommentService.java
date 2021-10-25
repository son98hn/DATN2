package com.example.datn.service.impl;

import com.example.datn.dto.CommentDTO;
import com.example.datn.entity.CommentEntity;
import com.example.datn.repository.CommentRepository;
import com.example.datn.repository.NewRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final NewRepository newRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, NewRepository newRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.newRepository = newRepository;
    }

    @Override
    public void save(CommentDTO commentDTO) {
        if(commentDTO.getId()!=null){
            CommentEntity oldCommentEntity = commentRepository.findById(commentDTO.getId()).get();
            oldCommentEntity.setContent(commentDTO.getContent());
            oldCommentEntity.setDate(LocalDateTime.now());
            commentRepository.save(oldCommentEntity);
        }
        else{
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(commentDTO.getContent());
            commentEntity.setDate(LocalDateTime.now());
            commentEntity.setNewEntity(newRepository.findById(commentDTO.getNewId()).get());
            commentEntity.setUserEntity(userRepository.findById(commentDTO.getUserId()).get());
            commentRepository.save(commentEntity);
        }
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentEntity> findAllByNewEntityId(Long newId) {
        return commentRepository.findAllByNewEntityId(newId);
    }
}
