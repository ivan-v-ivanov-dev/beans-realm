package com.personal.beans.constants;

public class Constants {

    // Logger constants
    public static final String POSTGRESQL_CONNECTION_POOL = "PostgreSQL Connection Pool";
    public static final String FROM_REDIS = "From REDIS :: ";
    public static final String FROM_POSTGRES = "From POSTGRES :: ";
    public static final String RETRIEVE_LATEST_BEANS_FROM_REDIS_CACHE = FROM_REDIS + "Retrieve Latest Beans";
    public static final String RETRIEVE_LATEST_BEANS_FROM_POSTGRES_DB = FROM_POSTGRES + "Retrieve Latest Beans";
    public static final String RETRIEVE_MOST_DOWNLOADED_BEANS_FROM_REDIS_CACHE =
            FROM_REDIS + "Retrieve Most Downloaded Beans";
    public static final String RETRIEVE_MOST_DOWNLOADED_BEANS_FROM_POSTGRES_DB =
            FROM_POSTGRES + "Retrieve Most Downloaded Beans";
    public static final String RETRIEVE_TOP_RATED_BEANS_FROM_REDIS_CACHE = FROM_REDIS + "Retrieve Top Rated Beans";
    public static final String RETRIEVE_TOP_RATED_BEANS_FROM_POSTGRES_DB = FROM_POSTGRES + "Retrieve Top Rated Beans";
    public static final String RETRIEVE_TOTAL_APPROVED_BEANS_COUNT_FROM_REDIS_CACHE =
            FROM_REDIS + "Retrieve Total Approved Beans Count";
    public static final String RETRIEVE_TOTAL_APPROVED_BEANS_COUNT_FROM_POSTGRES_DB =
            FROM_POSTGRES + "Retrieve Total Approved Beans Count";
    public static final String RETRIEVE_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS_FROM_REDIS_CACHE =
            FROM_REDIS + "Retrieve Total Download Count For All Approved Beans";
    public static final String RETRIEVE_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS_FROM_POSTGRES_DB =
            FROM_POSTGRES + "Retrieve Total Download Count For All Approved Beans";
    public static final String RETRIEVE_TOTAL_REGISTERED_USERS_FROM_REDIS_CACHE =
            FROM_REDIS + "Retrieve Total Registered Users";
    public static final String RETRIEVE_TOTAL_REGISTERED_USERS_FROM_POSTGRES_DB =
            FROM_POSTGRES + "Retrieve Total Registered Users";
    public static final String DELETE_REDIS_CACHE_AT_TIME_TEMPLATE = "Delete REDIS cache at time :: %s";

    // Repository constants
    public static final String NAME = "name";
    public static final String BEAN = "bean";
    public static final String CREATOR = "creator";
    public static final String TYPE = "type";
    public static final String TAG = "tag";
    public static final String DEVICE = "device";
    public static final String OFFSET = "offset";
    public static final String STATUS = "status";

    // Redis keys constants
    public static final String LATEST_BEANS = "LATEST_BEANS";
    public static final String MOST_DOWNLOADED_BEANS = "MOST_DOWNLOADED_BEANS";
    public static final String TOP_RATED_BEANS = "TOP_RATED_BEANS";
    public static final String TOTAL_APPROVED_BEANS_COUNT = "TOTAL_APPROVED_BEANS_COUNT";
    public static final String TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS = "TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS";
    public static final String TOTAL_REGISTERED_USERS = "TOTAL_REGISTERED_USERS";


    private Constants() {
    }
}
