package com.mango.amango.domain.user.repository;

import com.mango.amango.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findUserByEmail(String userId);

    Optional<User> findUserByNickname(String userId);
}
