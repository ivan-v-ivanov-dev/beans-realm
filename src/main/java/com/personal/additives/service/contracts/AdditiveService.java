package com.personal.additives.service.contracts;

import com.personal.additives.models.Additive;

import java.util.List;

public interface AdditiveService {

    Additive create(Additive additive);

    List<Additive> filter(String additive, String creator, String tag, String type, String device, int offset);

    List<Additive> findByStatus(String status,int offset);

}
