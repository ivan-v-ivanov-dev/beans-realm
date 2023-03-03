package com.personal.beans.service;

import com.personal.beans.models.comments.Comment;
import com.personal.beans.repository.mongo.CommentRepository;
import com.personal.beans.service.contracts.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findByBean(String beanName) {
        return this.commentRepository.findByBeanNameOrderByPostedDesc(beanName);
    }
}
