package com.personal.beans.config;

public class ConfigConstants {

    // Mongo Configuration Logger Constants
    public static final String MONGO_CONFIGURATION = "MongoDB Configuration :: ";
    public static final String MONGO_CONFIGURATION_SAMPLE_DATA_IMPORTED = MONGO_CONFIGURATION + "Sample Comments imported in database";
    public static final String MONGO_CONFIGURATION_MONGO_TEMPLATE_CREATED = MONGO_CONFIGURATION + "MongoTemplate Created";
    public static final String MONGO_CONFIGURATION_MONGO_CLIENT_CREATED = MONGO_CONFIGURATION + "MongoClient Created";

    // Postgres Configuration Logger Constants
    public static final String POSTGRES_CONNECTION_POOL = "Postgres Connection Pool";
    public static final String POSTGRES_CONFIGURATION = "Postgres Configuration :: ";
    public static final String POSTGRES_CONFIGURATION_HIKARI_DATA_SOURCE_CREATED = POSTGRES_CONFIGURATION + "Hikari DataSource Created";
    public static final String POSTGRES_CONFIGURATION_LOCAL_CONTAINER_ENTITY_MANAGER_FACTORY = POSTGRES_CONFIGURATION + "LocalContainerEntityManagerFactory Created";
    public static final String POSTGRES_CONFIGURATION_JPA_TRANSACTION_MANAGER_CREATED = POSTGRES_CONFIGURATION + "JpaTransactionManager Created";

    // Redis Configuration Logger Constants
    public static final String REDIS_CONFIGURATION = "Redis Configuration :: ";
    public static final String REDIS_CONFIGURATION_REDIS_STANDALONE_CONFIGURATION_CREATED = REDIS_CONFIGURATION + "RedisStandaloneConfiguration Created";
    public static final String REDIS_CONFIGURATION_JEDIS_CONNECTION_FACTORY_CREATED = REDIS_CONFIGURATION + "JedisConnectionFactory Created";
    public static final String REDIS_CONFIGURATION_OBJECT_MAPPER_CREATED = REDIS_CONFIGURATION + "ObjectMapper Created";
    public static final String REDIS_CONFIGURATION_JACKSON_2_JSON_REDIS_SERIALIZER_CREATED = REDIS_CONFIGURATION + "Jackson2JsonRedisSerializer Created";
    public static final String REDIS_CONFIGURATION_REDIS_TEMPLATE_CREATED = REDIS_CONFIGURATION + "RedisTemplate Created";


    private ConfigConstants() {
    }
}
