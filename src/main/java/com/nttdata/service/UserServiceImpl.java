package com.nttdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nttdata.model.User;
import com.nttdata.repository.UserRepository;
import com.nttdata.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@CachePut(key = "#id", value = "userCache")
	public User updateUser(User user, Long id) {
		System.out.println("called updateUser from DB");
		User userToUpdate = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		
		
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setUserName(user.getUserName());
		return userRepository.save(userToUpdate);
	}

	@Override
	@CacheEvict(key = "#id", value = "userCache")
	public void deleteUser(Long id) {
		System.out.println("called deleteUser from DB");
		User userToDelete = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("not found user"));
		userRepository.delete(userToDelete);
	}

	@Override
	@Cacheable(key = "#id",value = "userCache")
	public User getOneUser(Long id) {
		System.out.println("called getOneUser from DB");
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("user not found"));
	}

	@Override
	@Cacheable(value = "userCache")
	public List<User> getAllUser() {
		System.out.println("called getAllUser from DB");
		return (List<User>)userRepository.findAll();
	}
	
	

}
