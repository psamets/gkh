package com.spp.gkh.users.repository;

import com.spp.gkh.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersJpaRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(Integer age);
}
