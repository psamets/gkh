package com.spp.gkh.users.service;

import com.spp.gkh.users.dto.UpdateUserParams;
import com.spp.gkh.users.entity.User;
import com.spp.gkh.users.dto.UserDto;

import java.util.List;

//@Service
public interface UsersService {
    List<User> findAll();

    User save(UserDto user);

    User update(Long id, UpdateUserParams params);

    void delete(Long id);

    User findById(Long id);

    List<User> findAllByAge(Integer age);
}
