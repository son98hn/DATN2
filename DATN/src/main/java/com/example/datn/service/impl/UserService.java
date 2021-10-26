package com.example.datn.service.impl;


import com.example.datn.dto.UserDTO;
import com.example.datn.entity.GroupEntity;
import com.example.datn.entity.UserEntity;
import com.example.datn.entity.UserGroupEntity;
import com.example.datn.repository.GroupRepository;
import com.example.datn.repository.UserGroupRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.service.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String DefaultPassword = "Abc@12345678";
    private final
    EntityManager entityManager;
    private final UserGroupService userGroupService;

    public UserService(UserRepository userRepository, GroupRepository groupRepository, UserGroupRepository userGroupRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EntityManager entityManager, UserGroupService userGroupService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userGroupRepository = userGroupRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.entityManager = entityManager;
        this.userGroupService = userGroupService;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            UserEntity userEntity = new UserEntity();
            if (!verifyUsername(userDTO.getUsername()) && !verifyEmail(userDTO.getEmail()) && !verifyPhone(userDTO.getPhone())) {
                userEntity.setUsername(userDTO.getUsername());
                userEntity.setPassword(bCryptPasswordEncoder.encode(DefaultPassword));
                userEntity.setEmail(userDTO.getEmail());
                userEntity.setPhone(userDTO.getPhone());
                userEntity.setName(userDTO.getName());
                userEntity.setType("public");
                userRepository.save(userEntity);
                for (int i = 0; i < userDTO.getGroupName().size(); i++) {
                    UserGroupEntity userGroupEntity = new UserGroupEntity();
                    userGroupEntity.setUserEntity(userEntity);
                    userGroupEntity.setGroupEntity(groupRepository.findByName(userDTO.getGroupName().get(i)));
                    userGroupRepository.save(userGroupEntity);
                }
                userDTO.setMessage("Tạo tài khoản thành công");
            } else {
                userDTO.setMessage("Tải khoản đã được sử dụng");
            }
        } else {
            UserEntity oldUser = userRepository.findById(userDTO.getId()).get();
            if (!verifyUpdateUser(oldUser, userDTO) && verifyUsername(userDTO.getUsername()) && verifyEmail(userDTO.getEmail()) && verifyPhone(userDTO.getPhone())) {

                userDTO.setMessage("Số điện thoại hoặc Email đã tồn tại!");
            } else {
                oldUser.setEmail(userDTO.getEmail());
                oldUser.setPhone(userDTO.getPhone());
                oldUser.setName(userDTO.getName());
                oldUser.setAvatar(userDTO.getAvatar());
                oldUser.setType("public");
                userRepository.save(oldUser);
                for (int i = 0; i < userDTO.getGroupName().size(); i++) {
                    UserGroupEntity userGroupEntity = new UserGroupEntity();
                    userGroupEntity.setUserEntity(oldUser);
                    userGroupEntity.setGroupEntity(groupRepository.findByName(userDTO.getGroupName().get(i)));
                    userGroupRepository.save(userGroupEntity);
                }
                userDTO.setMessage("Cập nhật tài khoản thành công!");
            }
        }
    }

    private Boolean verifyUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private Boolean verifyEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private Boolean verifyPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    private Boolean verifyUpdateUser(UserEntity userEntity, UserDTO userDTO) {
        return userEntity.getEmail().equals(userDTO.getEmail()) && userEntity.getPhone().equals(userDTO.getPhone());
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userGroupRepository.deleteByUserEntityId(userEntity.getId());
            userRepository.deleteById(item);
        }
    }

    @Override
    public void resetPassword(long[] ids) {
        for (long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setPassword(bCryptPasswordEncoder.encode(DefaultPassword));
            userRepository.save(userEntity);
        }
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> findUser(Pageable pageable) {
        return userRepository.findUser(pageable);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Boolean verifyRegister(String password, String confirmPassword) {
        return (password.equals(confirmPassword));
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (verifyRegister(userDTO.getPassword(), userDTO.getConfirmPassword()) && !verifyEmail(userDTO.getUsername()) && !verifyPhone(userDTO.getPhone()) && !verifyEmail(userDTO.getEmail())) {
            UserEntity user = new UserEntity();
            user.setUsername(userDTO.getUsername());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
            UserGroupEntity userGroupEntity = new UserGroupEntity();
            userGroupEntity.setUserEntity(user);
            userGroupEntity.setGroupEntity(groupRepository.findByCode("user"));
            userGroupRepository.save(userGroupEntity);
            userDTO.setMessage("Đăng ký tài khoản thành công");
        } else {
            userDTO.setMessage("Đăng ký tài khoản thất bại");
        }
    }

    @Override
    public void updateProfile(UserDTO userDTO) {
        UserEntity oldUser = userRepository.findById(userDTO.getId()).get();
            if (!verifyUpdateUser(oldUser, userDTO) && verifyEmail(userDTO.getEmail()) && verifyPhone(userDTO.getPhone())                                                                                                   ) {
                userDTO.setMessage("Số điện thoại hoặc Email đã tồn tại!");
            } else {
                oldUser.setEmail(userDTO.getEmail());
                oldUser.setPhone(userDTO.getPhone());
                oldUser.setName(userDTO.getName());
                oldUser.setAvatar(userDTO.getAvatar());
                userRepository.save(oldUser);
                userDTO.setMessage("Cập nhật tài khoản thành công!");
            }
    }

    @Override
    public List<UserEntity> findByNew(Long newId) {
        return userRepository.findByNew(newId);
    }

    @Override
    public void updatePassword(UserEntity userEntity, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        userEntity.setPassword(encodedPassword);
        userEntity.setResetPasswordToken(null);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity!=null) {
            userEntity.setResetPasswordToken(token);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void processOAuthPostLogin(String username, String email) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity==null) {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setName(username);
            newUser.setEmail("FB_"+email);
            newUser.setPassword(bCryptPasswordEncoder.encode(DefaultPassword));
            newUser.setType("fb");
            userRepository.save(newUser);
        }
    }
}

