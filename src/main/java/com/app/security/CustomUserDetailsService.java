package com.app.security;

import com.app.dao.UserDAO;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<User> users = userDAO.getUserByEmail(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new CustomUserDetails(users.get(0));
    }
}
