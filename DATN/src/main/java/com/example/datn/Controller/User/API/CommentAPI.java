package com.example.datn.Controller.User.API;

import com.example.datn.dto.CommentDTO;
import com.example.datn.service.ICommentService;
import com.example.datn.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class CommentAPI {

    private final ICommentService commentService;

    private final IUserService userService;

    public CommentAPI(ICommentService commentService, IUserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping(value = "/api-user-comment", produces = "application/json;charset=UTF-8")
    public void createComment(@RequestBody CommentDTO model, Principal principal) {
        String userName = principal.getName();
        model.setUserId(userService.findByUserName(userName).getId());
        model.setNewId(model.getNewId());
        commentService.save(model);
    }

    @PutMapping(value = "/api-user-comment", produces = "application/json;charset=UTF-8")
    public void updateComment(@RequestBody CommentDTO model, Principal principal) {
        commentService.save(model);
    }

    @DeleteMapping(value = "/api-user-comment/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}

