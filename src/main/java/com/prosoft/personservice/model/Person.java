package com.prosoft.personservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель данных человека")
public class Person {

    @Schema(description = "Уникальный идентификатор человека", example = "123")
    private Long id;

    @Schema(description = "Имя человека", example = "Иван", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "Фамилия человека", example = "Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "Возраст человека", example = "30", minimum = "0", maximum = "150")
    private Integer age;
}