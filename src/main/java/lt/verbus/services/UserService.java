package lt.verbus.services;

import lt.verbus.model.User;
import lt.verbus.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
