package com.activity.Activity.Services;
import com.activity.Activity.Model.JWTResponseDto;
import com.activity.Activity.Model.LoginDto;
import com.activity.Activity.Model.RegisterDto;
public interface AuthService {
    JWTResponseDto login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
