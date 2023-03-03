package com.personal.beans.models.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(collection = "comments")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    @Indexed
    private String beanName;

    @Indexed
    private String author;

    private String text;

    @Indexed
    private LocalDate posted;

}
