package com.pixsee.services.login;

import com.pixsee.services.data.User;
import com.pixsee.services.data.UserRepository;
import com.pixsee.services.data.exception.InvalidPasswordException;
import com.pixsee.services.data.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.Future;

/**
 * Created by Tudor on 28-Oct-16.
 */
@Service
public class LoginService {
    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<User> login(User user) {
        Future<User> future = userRepository.findByEmail(user.getEmail());
        return Observable.from(future, Schedulers.io())
                .map(user1 -> checkNull(user1))
                .map(user1 -> checkPassword(user, user1))
                .map(user1 -> updatePushToken(user, user1));
    }

    private User checkNull(User user1) {
        if (user1 == null) throw new UserNotFoundException();
        return user1;
    }

    private User checkPassword(@RequestBody User user, User user1) {
        if (!user.getPassword().equals(user1.getPassword())) throw new InvalidPasswordException();
        return user1;
    }

    /**
     * If request user and database stored user have different push tokens,
     * update the database token
     *
     * @return database user
     */
    private User updatePushToken(@RequestBody User user, User user1) {
        if (!user.getPushToken().equals(user1.getPushToken()))
            userRepository.save(user);
        return user1;
    }

}
