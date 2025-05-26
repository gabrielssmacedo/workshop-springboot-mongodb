package com.gabrielmacedo.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielmacedo.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
