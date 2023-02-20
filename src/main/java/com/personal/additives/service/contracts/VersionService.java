package com.personal.additives.service.contracts;

import com.personal.additives.models.Version;

import java.util.List;

public interface VersionService {

    List<Version> findByAdditive(String additive);

}
