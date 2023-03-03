package com.personal.beans.service.contracts;

import com.personal.beans.models.Bean;
import com.personal.beans.models.Version;
import com.personal.beans.models.dto.BeanDto;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface EntityMapperService {

    Bean toBean(BeanDto beanDto) throws IOException;

    Version toFirstVersion(Bean bean, BeanDto beanDto) throws NoSuchAlgorithmException, IOException;
}
