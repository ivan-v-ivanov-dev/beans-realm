package com.personal.beans.models.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.personal.beans.constants.Constants.DESCRIPTION_CAN_NOT_BE_EMPTY;
import static com.personal.beans.constants.Constants.DESCRIPTION_MUST_BE_AT_LEAST_10_SYMBOLS;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentDto implements Serializable {

    private String beanName;

    @NotBlank(message = "Username can not be empty!")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 12 symbols!")
    private String author;

    @NotBlank(message = DESCRIPTION_CAN_NOT_BE_EMPTY)
    @Size(min = 10, message = DESCRIPTION_MUST_BE_AT_LEAST_10_SYMBOLS)
    private String text;

}
