package com.activity.Activity.Services;
import com.activity.Activity.Model.*;
import com.activity.Activity.Repository.RoleRepository;
import com.activity.Activity.Repository.UserRepository;
import com.activity.Activity.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public JWTResponseDto login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        JWTResponseDto jwtResponseDto=new JWTResponseDto();
        User user = userRepository.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            String token = jwtTokenProvider.generateToken(authentication);
            jwtResponseDto.setToken(token);
        }
        return jwtResponseDto;
    }
    @Override
    public String register(RegisterDto registerDto) {
        User newUser=new User();
        newUser.setName(registerDto.getName());
        newUser.setPassword(registerDto.getPassword());
        newUser.setEmail(registerDto.getEmail());
        newUser.setSurname(registerDto.getSurname());
        Set<Role> roleSet=new HashSet<>();
        Role role;
        if(registerDto.isUserType()){
            role=roleRepository.findByName("TEACHER")
                    .orElseThrow();
        }else {
            role=roleRepository.findByName("STUDENT")
                    .orElseThrow();
        }
        roleSet.add(role);
        newUser.setRoles(roleSet);
        User registeredUser=userRepository.save(newUser);
        String result;
        if(registeredUser!=null){
            result="kayıt basarili";
        }else {
            result="kayıt basarisiz";
        }
        return result;
    }
}
