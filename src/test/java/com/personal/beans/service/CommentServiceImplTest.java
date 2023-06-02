package com.personal.beans.service;

import com.personal.beans.repository.mongo.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentServiceImpl commentService;

    @Test
    public void findByBean_callsRepository() {
        commentService.findByBean(anyString());
        verify(commentRepository, times(1)).findByBeanNameOrderByPostedDesc(anyString());
    }

    @Test
    public void count_callsRepository() {
        commentService.count();
        verify(commentRepository, times(1)).count();
    }

    @Test
    public void countByBeanName_callsRepository() {
        commentService.countByBeanName(anyString());
        verify(commentRepository, times(1)).countByBeanName(anyString());
    }
}
