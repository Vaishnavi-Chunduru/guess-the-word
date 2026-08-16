package com.example.guess_the_word.repository;

import com.example.guess_the_word.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a User where the username column equals the given value
    Optional<User> findByUsername(String username);

    // Does a user with this username exist?
    boolean existsByUsername(String username);
}


/*
* Because of JpaRepository, we automatically get methods such as
1. save(user)
2. findById(id)
3. findAll()
4. delete(user)
5. deleteById(id)
6. count()
7. existsById(id)
*/