package com.superapp.auth_service.user;

import com.superapp.auth_service.exception.ExistingEmailException;
import com.superapp.auth_service.exception.ExistingIdException;
import com.superapp.auth_service.exception.ExistingUsernameException;
import com.superapp.auth_service.exception.NoUserFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserMapper userMapper;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        try {
            UserDto userCreated = userMapper.userToUserDto(userService.createUser(userDto));
            return new ResponseEntity<UserDto>(userCreated, HttpStatus.CREATED);

        } catch (ExistingIdException | ExistingUsernameException | ExistingEmailException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity findAll(){
        try {
            List<UserDto> users = userMapper.userListToUserDtoList(userService.findAll());
            return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        try {
            UserDto userFound = userMapper.userToUserDto(userService.findById(id));
            return new ResponseEntity<UserDto>(userFound,HttpStatus.FOUND);

        } catch (NoUserFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody UserDto userDto){
        try {
            UserDto userUpdated = userMapper.userToUserDto(userService.updateUser(userDto));
            return new ResponseEntity<UserDto>(userUpdated,HttpStatus.OK);

        } catch (NoUserFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestBody Integer id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoUserFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
