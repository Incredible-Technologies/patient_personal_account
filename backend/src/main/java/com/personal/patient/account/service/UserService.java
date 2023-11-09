package com.personal.patient.account.service;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.RegistrationUser;
import com.personal.patient.account.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;


    public Optional<User> findByUsername(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException((
                String.format("Пользователь '%s' не найден", username))
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationUser registrationUser){
        User user = new User();
        user.setEmail(registrationUser.getEmail());
        user.setEmail(registrationUser.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        user.setRoles(Set.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) throw new NotFoundException("user with such principal not found :" + principal);
        return userRepository.findByEmail(principal.getName()).orElseThrow(()-> new NotFoundException("user with such email not found :" + principal.getName()));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email " + email + " not found"));
    }
}
