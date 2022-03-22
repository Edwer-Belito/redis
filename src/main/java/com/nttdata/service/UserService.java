package com.nttdata.service;

import java.util.List;

import com.nttdata.model.User;

public interface UserService {

	public User saveUser(User user);
    public User updateUser(User user, Long id);
    public void deleteUser(Long id);
    public User getOneUser(Long id);
    public List<User> getAllUser();
}
