package com.personal.beans.service.contracts;

import com.personal.beans.models.comments.Comment;
import com.personal.beans.models.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findByBean(String beanName);

    int count();

    int countByBeanName(String beanName);

    Comment save(String beanName, CommentDto commentDto);
}
