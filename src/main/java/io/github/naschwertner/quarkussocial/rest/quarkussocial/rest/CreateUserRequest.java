package io.github.naschwertner.quarkussocial.rest.quarkussocial.rest;

public class CreateUserRequest {
    private String name;
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
}