package com.personal.beans.models.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.personal.beans.service.constants.ModelEntityConstants.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentDto implements Serializable {

    private String beanName;

    @NotBlank(message = USERNAME_CAN_NOT_BE_EMPTY)
    @Size(min = 4, max = 20, message = USERNAME_MUST_BE_BETWEEN_4_AND_12_SYMBOLS)
    private String author;

    @NotBlank(message = DESCRIPTION_CAN_NOT_BE_EMPTY)
    @Size(min = 10, message = DESCRIPTION_MUST_BE_AT_LEAST_10_SYMBOLS)
    private String text;

}
