package com.ebook.service;

import com.ebook.model.UserDtls;

public interface UserService {

	public UserDtls saveUser(UserDtls user);

//
	UserDtls getUserById(int id);
}


