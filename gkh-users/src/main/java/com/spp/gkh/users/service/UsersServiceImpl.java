package com.spp.gkh.users.service;

import com.spp.gkh.users.utils.RedisKeyGenerator;
import com.spp.gkh.users.dto.UpdateUserParams;
import com.spp.gkh.users.entity.User;
import com.spp.gkh.users.dto.UserDto;
import com.spp.gkh.users.exception.UserNotFoundException;
import com.spp.gkh.users.repository.UsersJpaRepository;
import com.spp.gkh.users.repository.UsersMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersMongoRepository usersMongoRepository;
    private final UsersJpaRepository usersJpaRepository;
    private final RedisServiceImpl redisService;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersMongoRepository") UsersMongoRepository usersMongoRepository, UsersJpaRepository usersJpaRepository, RedisServiceImpl redisService) {
        this.usersMongoRepository = usersMongoRepository;
        this.usersJpaRepository = usersJpaRepository;
        this.redisService = redisService;
    }

    public List<User> findAll() {
        return usersJpaRepository.findAll();
    }

    public User save(UserDto user) {
        User newUser = User.build(user);
        newUser = usersJpaRepository.save(newUser);
        //usersJpaRepository.save(user);
        //redisService.auditLog("user with ID = " + createdUser.getId() + " was created");
        return newUser;
    }

    public User update(Long id, UpdateUserParams params) throws UserNotFoundException {
        User user = usersJpaRepository.findById(id).orElseThrow(() -> new UserNotFoundException("The user not found!"));
        if (!Objects.requireNonNullElse(params.getName(), "").isEmpty()) {
            user.setName(params.getName());
        }
        if (params.getAge() != null) {
            user.setAge(params.getAge());
        }
        user = usersJpaRepository.save(user);
        redisService.delete(RedisKeyGenerator.generateUserKey(user.getId()));
        //redisService.auditLog("user with ID = " + updatedUser.getId() + " was updated");
        return user;
    }

    public void delete(Long userId) {
        User user = usersJpaRepository.findById(userId).orElse(null);
        if (user != null) {
            usersJpaRepository.delete(user);
            redisService.delete(RedisKeyGenerator.generateUserKey(user.getId()));
            //redisService.auditLog("user with ID = " + user.getId() + " was deleted");
        }
    }

    public User findById(Long id) {
        User user = redisService.get(RedisKeyGenerator.generateUserKey(id));
        if (user != null) {
            log.info("get user from redis with id {}", id);
            return user;
        }
        user = usersJpaRepository.findById(id).orElse(null);
        if (user != null) {
            log.info("set user to redis with id {}", id);
            redisService.set(RedisKeyGenerator.generateUserKey(user.getId()), user);
        }
        return user;
    }

    public List<User> findAllByAge(Integer age) {
        return usersJpaRepository.findAllByAge(age);
    }
}
