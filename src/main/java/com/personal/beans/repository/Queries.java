package com.personal.beans.repository;

public class Queries {

    public static final String FIND_LAST_SIX_UPLOADED_BEANS =
            "SELECT * FROM beans b " +
                    "WHERE b.id IN ( " +
                    "    SELECT ve.bean_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY b.upload_date DESC " +
                    "LIMIT 6";

    public static final String FIND_FIRST_SIX_MOST_DOWNLOADED_BEANS =
            "SELECT * FROM beans b " +
                    "WHERE b.id IN ( " +
                    "    SELECT ve.bean_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY ( " +
                    "    SELECT SUM(ver.download_count) " +
                    "    FROM versions ver " +
                    "    JOIN statuses s ON ver.status_id = s.id " +
                    "    WHERE s.name = 'Approved' AND " +
                    "    ver.bean_id = b.id ) DESC LIMIT 6";

    public static final String FIND_FIRST_SIX_TOP_RATED_BEANS =
            "SELECT * FROM beans b " +
                    "ORDER BY b.total_score / b.total_voters " +
                    "DESC LIMIT 6";

    public static final String FIND_APPROVED_BEANS_COUNT =
            "SELECT COUNT(*) FROM beans b " +
                    "WHERE b.id IN ( " +
                    "   SELECT v.bean_id FROM versions v " +
                    "   JOIN statuses s ON v.status_id = s.id " +
                    "   WHERE s.name = 'Approved')";

    public static final String FIND_TOTAL_APPROVED_BEAN_DOWNLOADS =
            "SELECT SUM(v.download_count) FROM versions v " +
                    "   JOIN statuses s ON v.status_id = s.id " +
                    "   WHERE s.name = 'Approved' ";

    public static final String BEAN_DOWNLOAD_COUNT =
            "SELECT SUM(v.download_count) " +
                    "FROM versions v " +
                    "JOIN beans b ON v.bean_id = b.id " +
                    "JOIN statuses s ON v.status_id = s.id " +
                    "WHERE s.name = 'Approved' AND " +
                    "b.name = :bean ";

    public static final String FIND_BEAN_BY_NAME =
            "SELECT b FROM Bean b " +
                    "WHERE b.name LIKE :beanName";

    public static final String FILTER_BEANS =
            "SELECT * FROM beans b " +
                    "JOIN tags ta ON b.tag_id = ta.id " +
                    "JOIN types ty ON b.type_id = ty.id " +
                    "JOIN devices de ON b.device_id = de.id " +
                    "WHERE (:tag is null OR ta.id = :tag) " +
                    "  AND (:type is null OR ty.id = :type) " +
                    "  AND (:device is null OR de.id = :device) " +
                    "  AND b.id IN ( " +
                    "    SELECT ve.bean_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY b.name ASC " +
                    "LIMIT 5 OFFSET :offset";

    public static final String FIND_NOT_APPROVED_BEANS =
            "SELECT * FROM beans b " +
                    "WHERE b.id IN ( " +
                    "   SELECT ve.bean_id " +
                    "   FROM versions ve " +
                    "   JOIN statuses s ON ve.status_id = s.id " +
                    "   WHERE s.name LIKE 'Waiting approval' ) " +
                    "ORDER BY b.name ASC ";

    public static final String FIND_ENABLED_USER_COUNT =
            "SELECT COUNT(u.id) FROM users u " +
                    "WHERE u.enabled = true AND " +
                    "u.admin = false ";

    public static final String FIND_ALL_USERS_ORDER_BY_USERNAME_ASC =
            "SELECT u FROM User u " +
                    "WHERE u.admin = false " +
                    "ORDER BY u.username ASC";

    public static final String FILTER_BEANS_APPROVED_VERSIONS =
            "SELECT v FROM Version v " +
                    "JOIN v.bean b " +
                    "JOIN v.status s " +
                    "WHERE b.name = :bean AND " +
                    "s.name = 'Approved' " +
                    "ORDER BY v.name DESC";

    public static final String FILTER_UNAPPROVED_VERSION_FOR_BEANS =
            "SELECT v FROM Version v " +
                    "JOIN v.bean b " +
                    "JOIN v.status s " +
                    "WHERE b.name = :bean AND " +
                    "s.name LIKE 'Waiting approval' " +
                    "ORDER BY v.name DESC";

    public static final String COUNT_APPROVED_VERSIONS_BY_BEAN_NAME =
            "SELECT COUNT(v.id) FROM versions v " +
                    "JOIN beans b ON v.bean_id = b.id " +
                    "JOIN statuses s ON v.status_id = s.id " +
                    "WHERE b.name = :beanName AND " +
                    "s.name LIKE 'Approved' ";

    public static final String FIND_APPROVED_BEANS_COUNT_BY_USERNAME =
            "SELECT COUNT(*) FROM beans b " +
                    "JOIN users u ON b.creator_id = u.id " +
                    "WHERE b.id IN ( " +
                    "   SELECT v.bean_id FROM versions v " +
                    "   JOIN statuses s ON v.status_id = s.id " +
                    "   WHERE s.name = 'Approved') AND " +
                    "   u.username = :username";

    private Queries() {
    }
}
