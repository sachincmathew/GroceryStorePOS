package com.store.repo;

import org.springframework.data.repository.CrudRepository;

import com.store.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
