package com.example.datn.repository;

import com.example.datn.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserEntity> findUser(Pageable pageable);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    UserEntity findByEmail(String email);

    @Query(value = "select * from users as u inner join comment as m on u.id=m.user_id WHERE m.new_id=?1",nativeQuery = true)
    List<UserEntity> findByNew(Long newId);

    UserEntity findByResetPasswordToken(String token);
}
