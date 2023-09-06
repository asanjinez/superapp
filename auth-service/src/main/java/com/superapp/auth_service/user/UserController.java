package com.superapp.auth_service.user;

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

    /**
     * Create a User
     * @param userDto User data for creating a new user.
     * @return ResponseEntity with the created user data and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        UserDto userCreated = userMapper.userToUserDto(userService.createUser(userDto));
        return new ResponseEntity<UserDto>(userCreated, HttpStatus.CREATED);
    }

    /**
     * Get All Users
     * @return ResponseEntity with a list of all users and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity findAll(){
        List<UserDto> users = userMapper.userListToUserDtoList(userService.findAll());
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    /**
     * Find User by ID
     * @param id User ID to retrieve a specific user.
     * @return ResponseEntity with the user data and HTTP status 302 (Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        UserDto userFound = userMapper.userToUserDto(userService.findById(id));
        return new ResponseEntity<UserDto>(userFound, HttpStatus.FOUND);
    }

    /**
     * Update User
     * @param userDto User data for updating an existing user.
     * @return ResponseEntity with the updated user data and HTTP status 200 (OK).
     */
    @PutMapping()
    public ResponseEntity update(@RequestBody UserDto userDto){
        UserDto userUpdated = userMapper.userToUserDto(userService.updateUser(userDto));
        return new ResponseEntity<UserDto>(userUpdated, HttpStatus.OK);
    }

    /**
     * Delete User
     * @param id User ID to delete a specific user.
     * @return ResponseEntity with HTTP status 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
