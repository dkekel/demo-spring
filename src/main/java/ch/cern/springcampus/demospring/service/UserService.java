package ch.cern.springcampus.demospring.service;

import ch.cern.springcampus.demospring.bean.User;
import ch.cern.springcampus.demospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsManager {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public void createUser(final UserDetails user) {
        User account = (User) user;
        account.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(account);
    }

    @Override
    public void updateUser(final UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(final String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.findById(username).orElse(null) != null;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElse(null);
    }
}
