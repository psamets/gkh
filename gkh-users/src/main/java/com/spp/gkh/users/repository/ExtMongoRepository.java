package com.spp.gkh.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtMongoRepository<T, ID> extends MongoRepository<T, ID> {
    default <T> T extFindById(ID id) {
        return (T) findById(id).orElse(null);
    }
}
