package com.personal.beans.service.constants;

public class LoggerConstants {

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
    public static final String FROM_REDIS_ALL_REGISTERED_USERS_COUNT = FROM_REDIS + "Registered Users count";
    public static final String FROM_POSTGRES_ALL_REGISTERED_USERS_COUNT = FROM_POSTGRES + "Registered Users count";
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
    public static final String FROM_REDIS_VERSIONS_COUNT_FOR_BEAN_TEMPLATE = FROM_REDIS + "Versions count for Bean %s";
    public static final String FROM_POSTGRES_VERSIONS_COUNT_FOR_BEAN_TEMPLATE = FROM_POSTGRES + "Versions count for Bean %s";
    public static final String APPROVE_VERSION_FOR_BEAN_TEMPLATE = "Approve version %s for bean %s";
    public static final String CHANGE_STATUS_OF_USER_TEMPLATE = "Change status of %s to %s";
    public static final String FROM_POSTGRES_ALL_USERS = FROM_POSTGRES + "All users";
    public static final String FROM_POSTGRES_NOT_APPROVED_BEANS = FROM_POSTGRES + "Unapproved Beans";
    public static final String FROM_POSTGRES_BEAN_DOWNLOAD_COUNT_FOR_BEAN_TEMPLATE = FROM_POSTGRES + "Bean download count for Beans %s";
    public static final String FROM_MONGO_TOTAL_COMMENTS_COUNT = "From MONGO :: Total Comments count";
    public static final String FROM_MONGO_COMMENTS_COUNT_PER_BEAN_TEMPLATE = "From MONGO :: Comments count per Bean %s";
    public static final String FROM_MONGO_COMMENTS_FOR_ALL_BEANS_TEMPLATE = "From MONGO :: Comments for Bean %s";

    private LoggerConstants() {
    }
}
