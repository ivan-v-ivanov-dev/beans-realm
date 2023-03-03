package com.personal.beans.repository.mongo;

import com.personal.beans.models.comments.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBeanNameOrderByPostedDesc(String beanName);

}
