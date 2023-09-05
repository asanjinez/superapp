package com.superapp.auth_service.user;

import com.superapp.auth_service.exception.ExistingEmailException;
import com.superapp.auth_service.exception.ExistingIdException;
import com.superapp.auth_service.exception.ExistingUsernameException;
import com.superapp.auth_service.exception.NoUserFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserJpaDao userJpaDao;

    @Autowired
    IUserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userJpaDao.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        try {
            if (userDto.getId() != null && userJpaDao.findById(userDto.getId()).isPresent())
                throw new ExistingIdException("Already exists a person with id " + userDto.getId());

            userJpaDao.findByUsername(userDto.getUsername()).ifPresent(user -> {
                throw new ExistingUsernameException("Already exists a person with username " + userDto.getUsername());
            });
            userJpaDao.findByEmail(userDto.getEmail()).ifPresent(user -> {
                throw new ExistingEmailException("Already exists a person with email " + userDto.getEmail());
            });

            return userJpaDao.save(userMapper.userDtoToUser(userDto));

        } catch (ExistingIdException|ExistingUsernameException|ExistingEmailException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException("Error creating user", e);
        }


    }

    @Override
    public User findById(Integer id) {
        try {
            return this.personExistsById(id);
        } catch (NoUserFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return this.personExistsByEmail(email);
        } catch (NoUserFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User findByUsername(String username){
        try {
            return this.personExistsByUsername(username);
        } catch (NoUserFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User updateUser(UserDto userDto) {
        try {
            User userToEdit = this.personExistsById(userDto.getId());
            userToEdit.setUsername(userDto.getUsername());
            userToEdit.setEmail(userDto.getEmail());
            userToEdit.setPassword(userDto.getPassword());
            userToEdit.setName(userDto.getName());
            userToEdit.setLastname(userDto.getLastname());
            userToEdit.setRole(userMapper.stringToRole(userDto.getRole()));

            return userJpaDao.save(userToEdit);
        } catch (NoUserFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            User userToDelete = this.personExistsById(id);
            userJpaDao.delete(userToDelete);
        } catch (NoUserFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }

    }

    private User personExistsById(Integer id){
        return userJpaDao.findById(id).orElseThrow(() -> new NoUserFoundException("Person with id: " + id + " doesn't exist"));
    }
    private User personExistsByEmail(String email){
        return userJpaDao.findByEmail(email).orElseThrow(() -> new NoUserFoundException("Person with email: " + email + " doesn't exist"));
    }
    private User personExistsByUsername(String username){
        return userJpaDao.findByUsername(username).orElseThrow(() -> new NoUserFoundException("Person with username: " + username + " doesn't exist"));
    }
}
