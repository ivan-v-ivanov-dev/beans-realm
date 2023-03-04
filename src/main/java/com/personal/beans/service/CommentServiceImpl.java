package com.personal.beans.service;

import com.personal.beans.models.comments.Comment;
import com.personal.beans.models.dto.CommentDto;
import com.personal.beans.repository.mongo.CommentRepository;
import com.personal.beans.service.contracts.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public int count() {
        return (int) this.commentRepository.count();
    }

    @Override
    public int countByBeanName(String beanName) {
        return this.commentRepository.countByBeanName(beanName);
    }

    @Override
    public Comment save(String beanName, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .beanName(beanName)
                .beanName(commentDto.getBeanName())
                .author(commentDto.getAuthor())
                .text(commentDto.getText())
                .posted(LocalDate.now())
                .build();

        return this.commentRepository.save(comment);
    }

}
