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
            commentRepository.save(Comment.builder().id("1").beanName("House of Mighty Heroes").author("user1").text("Very nice!").posted(LocalDate.of(2023, 03, 02)).build());
            commentRepository.save(Comment.builder().id("2").beanName("House of Mighty Heroes").author("user2").text("Good job!").posted(LocalDate.of(2023, 03, 03)).build());
            commentRepository.save(Comment.builder().id("3").beanName("House of Mighty Heroes").author("user4").text("I play this game for over a month.").posted(LocalDate.of(2023, 02, 06)).build());
            commentRepository.save(Comment.builder().id("4").beanName("House of Mighty Heroes").author("user6").text("I have downloaded it.").posted(LocalDate.of(2023, 03, 10)).build());
            commentRepository.save(Comment.builder().id("5").beanName("Mine all Resources").author("user3").text("I like this game.").posted(LocalDate.of(2023, 01, 03)).build());
            commentRepository.save(Comment.builder().id("6").beanName("Mine all Resources").author("user4").text("Good Job").posted(LocalDate.of(2023, 02, 25)).build());
            commentRepository.save(Comment.builder().id("7").beanName("Mine all Resources").author("user2").text("Well looks good, but i have played better games.").posted(LocalDate.of(2023, 02, 14)).build());
            commentRepository.save(Comment.builder().id("8").beanName("Mine all Resources").author("user6").text("I like the graphics.").posted(LocalDate.of(2023, 01, 17)).build());
            commentRepository.save(Comment.builder().id("9").beanName("Mine all Resources 2").author("user6").text("First version was better.").posted(LocalDate.of(2023, 01, 21)).build());
            commentRepository.save(Comment.builder().id("10").beanName("Mine all Resources 2").author("user2").text("First version had better sound.").posted(LocalDate.of(2023, 03, 01)).build());
            commentRepository.save(Comment.builder().id("11").beanName("Mine all Resources 2").author("user4").text("It runs slowly on older PC.").posted(LocalDate.of(2023, 01, 24)).build());
            commentRepository.save(Comment.builder().id("12").beanName("Epic Adventures").author("user4").text("It is not that interesting.").posted(LocalDate.of(2023, 02, 19)).build());
            commentRepository.save(Comment.builder().id("13").beanName("Epic Adventures").author("user5").text("Wow, now that's something.").posted(LocalDate.of(2023, 02, 25)).build());
            commentRepository.save(Comment.builder().id("14").beanName("Epic Adventures").author("user1").text("It is interesting.").posted(LocalDate.of(2023, 01, 26)).build());
            commentRepository.save(Comment.builder().id("15").beanName("Mario and Friends").author("user1").text("It is interesting.").posted(LocalDate.of(2023, 01, 29)).build());
            commentRepository.save(Comment.builder().id("16").beanName("Mario and Friends").author("user3").text("It is an adventure to play it.").posted(LocalDate.of(2023, 03, 10)).build());
            commentRepository.save(Comment.builder().id("17").beanName("Mario and Friends").author("user5").text("Yes, i have downloaded it yesterday and it is interesting.").posted(LocalDate.of(2023, 03, 11)).build());
            commentRepository.save(Comment.builder().id("18").beanName("PC Operation System").author("user1").text("It has enhanced features - run faster.").posted(LocalDate.of(2023, 03, 11)).build());
            commentRepository.save(Comment.builder().id("19").beanName("PC Operation System").author("user4").text("Great controllers. An the icons are styled perfectly.").posted(LocalDate.of(2023, 02, 11)).build());
            commentRepository.save(Comment.builder().id("20").beanName("PC Operation System").author("user5").text("It has additional menus and it is very comfortable.").posted(LocalDate.of(2023, 01, 24)).build());
            commentRepository.save(Comment.builder().id("21").beanName("Mobile Operating System").author("user1").text("My phone runs better now.").posted(LocalDate.of(2023, 01, 16)).build());
            commentRepository.save(Comment.builder().id("22").beanName("Mobile Operating System").author("user2").text("There is more color contrast in the new layout.").posted(LocalDate.of(2023, 03, 9)).build());
            commentRepository.save(Comment.builder().id("23").beanName("Mobile Operating System").author("user6").text("Menus are more intuitive.").posted(LocalDate.of(2023, 02, 04)).build());
            commentRepository.save(Comment.builder().id("24").beanName("Mobile Operating System").author("user5").text("Looks good.").posted(LocalDate.of(2022, 12, 07)).build());
            commentRepository.save(Comment.builder().id("25").beanName("TV Driver").author("user5").text("Looks good.").posted(LocalDate.of(2022, 12, 07)).build());
            commentRepository.save(Comment.builder().id("26").beanName("TV Driver").author("user1").text("Switches between the channels more easily.").posted(LocalDate.of(2022, 11, 06)).build());
            commentRepository.save(Comment.builder().id("27").beanName("TV Driver").author("user3").text("It runs slowly on older TVs.").posted(LocalDate.of(2022, 10, 12)).build());
            commentRepository.save(Comment.builder().id("28").beanName("TV Driver").author("user5").text("Good job.").posted(LocalDate.of(2023, 02, 21)).build());
            log.info(MONGO_CONFIGURATION_SAMPLE_DATA_IMPORTED);
        };
    }
}
