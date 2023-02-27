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

    public static final String FILTER_BEANS =
            "SELECT * FROM beans b " +
                    "JOIN users us ON b.creator_id = us.id " +
                    "JOIN tags ta ON b.tag_id = ta.id " +
                    "JOIN types ty ON b.type_id = ty.id " +
                    "JOIN devices de ON b.device_id = de.id " +
                    "WHERE (:bean is null OR b.name = :bean) " +
                    "  AND (:creator is null OR us.username = :creator) " +
                    "  AND (:tag is null OR ta.name = :tag) " +
                    "  AND (:type is null OR ty.name = :type) " +
                    "  AND (:device is null OR de.name = :device) " +
                    "  AND b.id IN ( " +
                    "    SELECT ve.bean_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY b.name ASC " +
                    "LIMIT 10 OFFSET :offset";

    public static final String FIND_BEANS_BY_STATUS =
            "SELECT * FROM beans b " +
                    "WHERE b.id IN ( " +
                    "   SELECT ve.bean_id " +
                    "   FROM versions ve " +
                    "   JOIN statuses s ON ve.status_id = s.id " +
                    "   WHERE s.name = :status) " +
                    "ORDER BY b.name ASC " +
                    "LIMIT 10 OFFSET :offset";

    public static final String FIND_ENABLED_USER_COUNT =
            "SELECT COUNT(u.id) FROM users u " +
                    "WHERE u.enabled = true AND " +
                    "u.admin = false ";

    public static final String FILTER_USERS =
            "SELECT u FROM User u " +
                    "WHERE u.admin = false AND " +
                    "u.enabled = :enabled AND " +
                    "(:username is null OR username = :username) " +
                    "ORDER BY u.username ASC";

    public static final String FILTER_BEANS_APPROVED_VERSIONS =
            "SELECT v FROM Version v " +
                    "JOIN v.bean b " +
                    "JOIN v.status s " +
                    "WHERE b.name = :bean AND " +
                    "s.name = 'Approved' " +
                    "ORDER BY v.name DESC";

    public static final String FILTER_BEANS_TO_APPROVE =
            "SELECT v FROM Version v " +
                    "JOIN v.bean b " +
                    "JOIN v.status s " +
                    "WHERE b.name = :bean AND " +
                    "s.name NOT LIKE 'Approved' " +
                    "ORDER BY v.name DESC";

    private Queries() {
    }
}
