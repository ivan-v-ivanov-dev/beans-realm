package com.personal.beans.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.personal.beans.models.comments.Comment;
import com.personal.beans.repository.mongo.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;

import static com.personal.beans.config.ConfigConstants.*;

@Configuration
@EnableMongoRepositories(basePackages = {"com.personal.beans.repository.mongo"})
@Slf4j
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

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        log.info(MONGO_CONFIGURATION_MONGO_CLIENT_CREATED);
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), database);
        log.info(MONGO_CONFIGURATION_MONGO_TEMPLATE_CREATED);
        return mongoTemplate;
    }

    @Bean
    CommandLineRunner commandLineRunner(CommentRepository commentRepository) {
        return strings -> {
            commentRepository.save(new Comment("1", "House of Mighty Heroes", "user1", "First Test comment", LocalDate.of(2023, 03, 02)));
            commentRepository.save(new Comment("2", "House of Mighty Heroes", "user1", "First Test comment", LocalDate.of(2023, 03, 03)));
            log.info(MONGO_CONFIGURATION_SAMPLE_DATA_IMPORTED);
        };
    }
}
