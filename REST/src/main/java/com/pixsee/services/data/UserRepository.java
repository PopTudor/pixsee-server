package com.pixsee.services.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

/**
 * Created by Tudor on 28-Oct-16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Future<User> findByEmail(String email);

    User findByUserName(String userName);

    User findByPhone(String phone);
}
