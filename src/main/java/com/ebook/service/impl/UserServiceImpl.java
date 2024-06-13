package com.ebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebook.model.UserDtls;
import com.ebook.repository.UserRepository;
import com.ebook.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDtls saveUser(UserDtls user) {
		user.setRole("ROLE_USER");
		String encodePassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		UserDtls saveUser = userRepository.save(user);
		return saveUser;
	}

	@Override
	public UserDtls getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

}
