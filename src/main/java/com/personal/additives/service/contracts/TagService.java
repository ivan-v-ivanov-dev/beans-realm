package com.personal.additives.service.contracts;

import com.personal.additives.models.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAllOrderedByNameAsc();
}
