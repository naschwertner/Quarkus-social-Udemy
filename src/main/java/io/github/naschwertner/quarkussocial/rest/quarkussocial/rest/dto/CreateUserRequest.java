package io.github.naschwertner.quarkussocial.rest.quarkussocial.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateUserRequest {
    @NotBlank(message = "Name is Required")
    private String name;
    @NotNull(message = "Age is required")
    private Integer age;



}