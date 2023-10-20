package com.spp.gkh.users.repository;

//import org.springframework.data.mongodb.repository.MongoRepository;

import com.spp.gkh.users.entity.AuditLog;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMongoRepository extends ExtMongoRepository<AuditLog, String> {
    //private List<User> users = new ArrayList<>();
    //public User findUserById(String id) {
    //return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    //}

    //List<User> findAllByAge(Integer age);


}
