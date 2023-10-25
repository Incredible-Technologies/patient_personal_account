package com.vista.service;

import com.vista.entities.User;
import com.vista.exceptions.AppError;
import com.vista.models.JwtRequest;
import com.vista.models.JwtResponse;
import com.vista.models.RegistrationUser;
import com.vista.models.UserResponse;
import com.vista.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserSevice userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);}
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(RegistrationUser registrationUser){
        if(!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if(userService.findByUsername(registrationUser.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользовательс указанными именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUser);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail()));
    }
}

