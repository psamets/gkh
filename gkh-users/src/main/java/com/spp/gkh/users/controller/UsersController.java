package com.spp.gkh.users.controller;

import com.spp.gkh.users.dto.UpdateUserParams;
import com.spp.gkh.users.entity.User;
import com.spp.gkh.users.dto.UserDto;
import com.spp.gkh.users.exception.UserNotFoundException;
import com.spp.gkh.users.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
////@EnableCaching
public class UsersController {

    //private final UsersRepository usersRepository;
    //private final RedisTemplate<String, String> redisTemplate;
    //private ValueOperations<String, String> valueOperations;

    /*@Autowired
    public UsersController(UsersRepository usersRepository, RedisTemplate<String, String> redisTemplate) {
        this.usersRepository = usersRepository;
        this.redisTemplate = redisTemplate;
    }*/

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /*@PostConstruct
    public void init()
    {
        valueOperations = redisTemplate.opsForValue();
    }*/

    /*//@RequestMapping(method = RequestMethod.GET, value = "/greeting")
    @GetMapping(value = "/greeting")
    //public @ResponseBody String greeting(@RequestParam(name = "q", required = false) String value) {
    public String greeting(@RequestParam(name = "q", required = false) String value) {
        return "Hello " + value + "!";
    }*/

    @GetMapping
    public List<User> allUsers() {
        return usersService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody final UserDto user, HttpServletRequest request) {
        return usersService.save(user);
    }

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable("id") final Long id) {
        return usersService.findById(id);
    }

    @GetMapping("/byAge")
    public List<User> findAllByAge(@RequestParam final Integer age) {
        return usersService.findAllByAge(age);
    }

    //@PutMapping("/edit")
    @PutMapping("/{id}")
    public User update(@PathVariable("id") final Long id, @RequestBody final UpdateUserParams params) {
        return usersService.update(id, params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) {
        usersService.delete(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            UserNotFoundException ex) {
        log.error("User not found", ex);
        return ResponseEntity.notFound().build();
    }

}
