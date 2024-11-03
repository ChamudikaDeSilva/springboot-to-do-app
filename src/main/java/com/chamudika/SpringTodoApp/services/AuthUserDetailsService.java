package com.chamudika.SpringTodoApp.services;

import com.chamudika.SpringTodoApp.models.AuthUserDetails;
import com.chamudika.SpringTodoApp.models.User;
import com.chamudika.SpringTodoApp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    final UserRepository userRepo;

    public AuthUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));

        return user.map(AuthUserDetails::new).get();
    }
}
