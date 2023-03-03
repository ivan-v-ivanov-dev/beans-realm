package com.personal.beans.service.contracts;

import com.personal.beans.models.comments.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findByBean(String beanName);

}
