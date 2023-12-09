package org.document.category.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CategoryDTO {
    @NotNull
    private String code;
    @NotNull
    private String description;
    @NotNull
    private String image;
}
