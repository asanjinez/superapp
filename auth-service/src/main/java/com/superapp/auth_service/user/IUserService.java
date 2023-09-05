package com.superapp.auth_service.user;

import java.util.List;

public interface IUserService {
    public List<User> findAll();
    public User createUser(UserDto userDto);
    public User findById(Integer id);
    public User findByEmail(String email);
    public User findByUsername(String username);
    public User updateUser(UserDto userDto);
    public void deleteUser(Integer id);

}
