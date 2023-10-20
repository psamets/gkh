package com.spp.gkh.users.entity;

import com.spp.gkh.users.dto.UserDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
////@RedisHash("users")
@Table(name = "users")
@Entity(name = "users")
//The class for storing in Postgres
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;

    public static User build(UserDto userDto) {
        User newUser =  new User();
        newUser.setName(userDto.getName());
        newUser.setAge(userDto.getAge());
        return newUser;
    }
}
