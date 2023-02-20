package com.personal.additives.repository;

public class Queries {

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

    public static final String FILTER_USERS =
            "SELECT u FROM User u " +
                    "WHERE u.admin = false AND " +
                    "u.enabled = :enabled AND " +
                    "(:username is null OR username = :username) " +
                    "ORDER BY u.username ASC";

    private Queries() {
    }
}
