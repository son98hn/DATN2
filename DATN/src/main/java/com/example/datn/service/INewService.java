package com.example.datn.service;

import com.example.datn.dto.NewDTO;
import com.example.datn.entity.NewEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
    void saveNew(NewDTO newDTO);

    void delete(long[] ids);

    int totalItemActive();

    int totalItemDeactive();

    NewEntity findById(Long id);

    List<NewEntity> findNewsByCategoryParentCode(String categoryParentCode, Pageable pageable);

    List<NewEntity> findNewsByCategoryParentCode1(String categoryParentCode);

    List<NewEntity> findAllActive(Pageable pageable);

    List<NewEntity> findAllDeactive(Pageable pageable);

    int totalItemByCategoryParent(String categoryParent);

    int totalItemByCategory(Long categoryId);

    NewEntity findTopByCategoryParentCode(String categoryParentCode);

    NewEntity findByTitle(String title);

    List<NewEntity> findByCreatedBy(String username, Pageable pageable);

    int coutAllByCreatedBy(String username);

    List<NewEntity> findTop3ByTag(String tag);

    List<NewEntity> findByCategoryEntityId(Long categoryId);

    List<NewEntity> findByCategoryEntityIdPage(Long categoryId, Pageable pageable);

    List<NewEntity> search(String title,Pageable pageable);

    int countSearch(String title);
}
