package com.personal.beans.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.personal.beans.models.comments.Comment;
import com.personal.beans.repository.mongo.CommentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;

@Configuration
@EnableMongoRepositories(basePackages = {"com.personal.beans.repository.mongo"})
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings =
                MongoClientSettings
                        .builder()
                        .applyConnectionString(connectionString)
                        .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), database);
    }

    @Bean
    CommandLineRunner commandLineRunner(CommentRepository commentRepository) {
        return strings -> {
            commentRepository.save(new Comment("1", "House of Mighty Heroes", "user1", "First Test comment", LocalDate.of(2023, 03, 02)));
            commentRepository.save(new Comment("2", "House of Mighty Heroes", "user1", "First Test comment", LocalDate.of(2023, 03, 03)));
        };
    }
}
