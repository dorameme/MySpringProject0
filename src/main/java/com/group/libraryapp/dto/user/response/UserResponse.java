package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse
{
    private final long id;
    private final String name;
    private final Integer age;

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserResponse(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.age=user.getAge();
    }
    public long getId()
    {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
