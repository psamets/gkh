package com.spp.gkh.users.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


////@RedisHash("users")
@Data
@Document(collection = "users")
//The class for storing in Mongo
public class UserDocument {
    @Id
    private Long id;
    private String name;
    private Integer age;
}
