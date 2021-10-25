package com.example.datn.repository;

import com.example.datn.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByNewEntityId(Long newId);

    void deleteAllByNewEntityId(Long newId);
}
