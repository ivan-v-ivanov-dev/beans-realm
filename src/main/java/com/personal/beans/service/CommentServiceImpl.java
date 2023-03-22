package com.personal.beans.service;

import com.personal.beans.models.comments.Comment;
import com.personal.beans.models.dto.CommentDto;
import com.personal.beans.repository.mongo.CommentRepository;
import com.personal.beans.service.contracts.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.*;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findByBean(String beanName) {
        log.info(FROM_MONGO_COMMENTS_FOR_ALL_BEANS_TEMPLATE);
        return this.commentRepository.findByBeanNameOrderByPostedDesc(beanName);
    }

    @Override
    public int count() {
        int count = (int) this.commentRepository.count();
        log.info(FROM_MONGO_TOTAL_COMMENTS_COUNT);
        return count;
    }

    @Override
    public int countByBeanName(String beanName) {
        int count = this.commentRepository.countByBeanName(beanName);
        log.info(String.format(FROM_MONGO_COMMENTS_COUNT_PER_BEAN_TEMPLATE, beanName));
        return count;
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
