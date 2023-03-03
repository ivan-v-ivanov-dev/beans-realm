package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.models.Version;
import com.personal.beans.models.dto.BeanDto;
import com.personal.beans.repository.postgres.*;
import com.personal.beans.service.contracts.EntityMapperService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

import static com.personal.beans.constants.Constants.*;

@Service
public class EntityMapperServiceImpl implements EntityMapperService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final TypeRepository typeRepository;
    private final TagRepository tagRepository;
    private final StatusRepository statusRepository;

    public EntityMapperServiceImpl(UserRepository userRepository,
                                   DeviceRepository deviceRepository,
                                   TypeRepository typeRepository,
                                   TagRepository tagRepository,
                                   StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.typeRepository = typeRepository;
        this.tagRepository = tagRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Bean toBean(BeanDto beanDto) throws IOException {
        Bean bean = new Bean();
        bean.setName(beanDto.getName());
        bean.setImage(DATA_IMAGE_PNG_BASE64 + Base64.getEncoder().encodeToString(beanDto.getImage().getBytes()));
        bean.setCreator(this.userRepository.findByUsername("user1"));
        bean.setDevice(this.deviceRepository.findById(beanDto.getDeviceId()).orElseThrow());
        bean.setType(this.typeRepository.findById(beanDto.getTypeId()).orElseThrow());
        bean.setTag(this.tagRepository.findById(beanDto.getTagId()).orElseThrow());
        bean.setDescription(beanDto.getDescription());
        bean.setUploadDate(LocalDate.now());
        bean.setTotalVoters(0);
        bean.setTotalScore(0);
        return bean;
    }

    @Override
    public Version toFirstVersion(Bean bean, BeanDto beanDto) throws NoSuchAlgorithmException, IOException {
        Version version = new Version();
        version.setName(ONE_AS_STRING);
        version.setContent(beanDto.getContent().getBytes());
        version.setBean(bean);
        version.setStatus(this.statusRepository.findByName(WAITING_APPROVAL));
        version.setUploadDate(LocalDate.now());
        version.setModifications(FIRST_VERSION);
        version.setRepositoryUrl(beanDto.getRepositoryUrl());
        byte[] shaArr = MessageDigest.getInstance(SHA_256).digest(beanDto.getContent().getBytes());
        version.setSha256Checksum(Base64.getEncoder().encodeToString(shaArr));
        version.setDownloadCount(0);
        return version;
    }
}
