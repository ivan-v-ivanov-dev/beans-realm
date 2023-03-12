package com.personal.beans.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.personal.beans.service.constants.ModelEntityConstants.*;

@NoArgsConstructor
@Getter
@Setter
public class BeanDto implements Serializable {

    @NotBlank(message = NAME_CAN_NOT_BE_EMPTY)
    @Size(min = 4, max = 30, message = NAME_MUST_BE_BETWEEN_4_AND_30_SYMBOLS)
    private String name;

    @Positive(message = YOU_MUST_SELECT_A_TYPE)
    private int typeId;

    @Positive(message = YOU_MUST_SELECT_A_TAG)
    private int tagId;

    @Positive(message = YOU_MUST_SELECT_A_DEVICE)
    private int deviceId;

    @NotBlank(message = DESCRIPTION_CAN_NOT_BE_EMPTY)
    @Size(min = 10, message = DESCRIPTION_MUST_BE_AT_LEAST_10_SYMBOLS)
    private String description;

    @NotBlank(message = REPOSITORY_URL_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = REPOSITORY_URL_REG_EX_PATTERN,
            message = REPOSITORY_URL_MUST_BE_A_GIT_HUB_REPOSITORY)
    private String repositoryUrl;

    private MultipartFile image;

    private MultipartFile content;
}
