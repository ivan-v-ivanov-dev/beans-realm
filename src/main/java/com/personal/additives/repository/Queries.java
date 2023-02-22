package com.personal.additives.repository;

public class Queries {

    public static final String FIND_LAST_SIX_UPLOADED_ADDITIVES =
            "SELECT * FROM additives ad " +
                    "WHERE ad.id IN ( " +
                    "    SELECT ve.additive_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY ad.upload_date DESC " +
                    "LIMIT 6";

    public static final String FIND_FIRST_SIX_MOST_DOWNLOADED_ADDITIVES =
            "SELECT * FROM additives ad " +
                    "WHERE ad.id IN ( " +
                    "    SELECT ve.additive_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY ( " +
                    "    SELECT SUM(ver.download_count) " +
                    "    FROM versions ver " +
                    "    JOIN statuses s ON ver.status_id = s.id " +
                    "    WHERE s.name = 'Approved' AND " +
                    "    ver.additive_id = ad.id ) DESC LIMIT 6";

    public static final String FIND_FIRST_SIX_TOP_RATED_ADDITIVES =
            "SELECT * FROM additives ad " +
                    "ORDER BY ad. total_score / ad.total_voters " +
                    "DESC LIMIT 6";

    public static final String FIND_APPROVED_BEANS_COUNT =
            "SELECT COUNT(*) FROM additives ad " +
                    "WHERE ad.id IN ( " +
                    "   SELECT v.additive_id FROM versions v " +
                    "   JOIN statuses s ON v.status_id = s.id " +
                    "   WHERE s.name = 'Approved')";

    public static final String FIND_TOTAL_APPROVED_BEAN_DOWNLOADS =
            "SELECT SUM(v.download_count) FROM versions v " +
                    "   JOIN statuses s ON v.status_id = s.id " +
                    "   WHERE s.name = 'Approved' ";

    public static final String FILTER_ADDITIVE =
            "SELECT * FROM additives ad " +
                    "JOIN users us ON ad.creator_id = us.id " +
                    "JOIN tags ta ON ad.tag_id = ta.id " +
                    "JOIN types ty ON ad.type_id = ty.id " +
                    "JOIN devices de ON ad.device_id = de.id " +
                    "WHERE (:additive is null OR ad.name = :additive) " +
                    "  AND (:creator is null OR us.username = :creator) " +
                    "  AND (:tag is null OR ta.name = :tag) " +
                    "  AND (:type is null OR ty.name = :type) " +
                    "  AND (:device is null OR de.name = :device) " +
                    "  AND ad.id IN ( " +
                    "    SELECT ve.additive_id " +
                    "    FROM versions ve " +
                    "    JOIN statuses s ON ve.status_id = s.id " +
                    "    WHERE s.name = 'Approved') " +
                    "ORDER BY ad.name ASC " +
                    "LIMIT 10 OFFSET :offset";

    public static final String FIND_ADDITIVE_BY_STATUS =
            "SELECT * FROM additives ad " +
                    "WHERE ad.id IN ( " +
                    "   SELECT ve.additive_id " +
                    "   FROM versions ve " +
                    "   JOIN statuses s ON ve.status_id = s.id " +
                    "   WHERE s.name = :status) " +
                    "ORDER BY ad.name ASC " +
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

    public static final String FILTER_ADDITIVE_APPROVED_VERSIONS =
            "SELECT v FROM Version v " +
                    "JOIN v.additive ad " +
                    "JOIN v.status s " +
                    "WHERE ad.name = :additive AND " +
                    "s.name = 'Approved' " +
                    "ORDER BY v.name DESC";

    public static final String FILTER_ADDITIVE_TO_APPROVE =
            "SELECT v FROM Version v " +
                    "JOIN v.additive ad " +
                    "JOIN v.status s " +
                    "WHERE ad.name = :additive AND " +
                    "s.name NOT LIKE 'Approved' " +
                    "ORDER BY v.name DESC";

    private Queries() {
    }
}
