package com.efbet.travel.service.user;

import com.efbet.travel.domain.entity.User;
import com.efbet.travel.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}
