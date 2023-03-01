package com.personal.beans.constants;

public class Constants {

    // Logger constants
    public static final String POSTGRESQL_CONNECTION_POOL = "PostgreSQL Connection Pool";
    public static final String FROM_REDIS = "From REDIS :: ";
    public static final String FROM_POSTGRES = "From POSTGRES :: ";
    public static final String FROM_REDIS_LATEST_BEANS = FROM_REDIS + "Latest Beans";
    public static final String FROM_POSTGRES_LATEST_BEANS = FROM_POSTGRES + "Latest Beans";
    public static final String FROM_REDIS_MOST_DOWNLOADED_BEANS = FROM_REDIS + "Most Downloaded Beans";
    public static final String FROM_POSTGRES_MOST_DOWNLOADED_BEANS = FROM_POSTGRES + "Most Downloaded Beans";
    public static final String FROM_REDIS_TOP_RATED_BEANS = FROM_REDIS + "Top Rated Beans";
    public static final String FROM_POSTGRES_TOP_RATED_BEANS = FROM_POSTGRES + "Top Rated Beans";
    public static final String FROM_REDIS_TOTAL_APPROVED_BEANS_COUNT = FROM_REDIS + "Total Approved Beans Count";
    public static final String FROM_POSTGRES_TOTAL_APPROVED_BEANS_COUNT = FROM_POSTGRES + "Total Approved Beans Count";
    public static final String FROM_REDIS_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS = FROM_REDIS + "Total Download Count For All Approved Beans";
    public static final String FROM_POSTGRES_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS = FROM_POSTGRES + "Total Download Count For All Approved Beans";
    public static final String FROM_REDIS_TOTAL_REGISTERED_USERS = FROM_REDIS + "Total Registered Users";
    public static final String FROM_POSTGRES_TOTAL_REGISTERED_USERS = FROM_POSTGRES + "Total Registered Users";
    public static final String DELETE_REDIS_CACHE_AT_TIME_TEMPLATE = "Delete REDIS cache at time :: %s";
    public static final String FROM_REDIS_SINGLE_BEAN_TEMPLATE = FROM_REDIS + "Bean %s";
    public static final String FROM_POSTGRES_SINGLE_BEAN_TEMPLATE = FROM_POSTGRES + "Bean %s";
    public static final String FROM_REDIS_VERSIONS_FOR_BEAN_TEMPLATE = FROM_REDIS + "Versions for Bean %s";
    public static final String FROM_POSTGRES_VERSIONS_FOR_BEAN_TEMPLATE = FROM_POSTGRES + "Versions for Bean %s";
    public static final String FROM_REDIS_ALL_TAGS = FROM_REDIS + "All Tags";
    public static final String FROM_POSTGRES_ALL_TAGS = FROM_POSTGRES + "All Tags";
    public static final String FROM_REDIS_ALL_TYPES = FROM_REDIS + "All Types";
    public static final String FROM_POSTGRES_ALL_TYPES = FROM_POSTGRES + "All Types";
    public static final String FROM_REDIS_ALL_DEVICES = FROM_REDIS + "All Devices";
    public static final String FROM_POSTGRES_ALL_DEVICES = FROM_POSTGRES + "All Devices";

    // Repository constants
    public static final String NAME = "name";
    public static final String BEAN = "bean";
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
    public static final String ALL_TAGS = "ALL_TAGS";
    public static final String ALL_TYPES = "ALL_TYPES";
    public static final String ALL_DEVICES = "ALL_DEVICES";


    // Service constants
    public static final String EMPTY_SPACE = " ";
    public static final String UNDERSCORE = "_";
    public static final String TIME_FORMAT_TEMPLATE = "dd-MM-yyyy HH:mm:ss";
    public static final String BEAN_ = "BEAN_";
    public static final String _VERSIONS = "_VERSIONS";

    private Constants() {
    }
}
