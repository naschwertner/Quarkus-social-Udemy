package io.github.naschwertner.quarkussocial.rest.quarkussocial.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotBlank(message = "Name is Required")
    private String name;
    @NotNull(message = "Age is required")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}