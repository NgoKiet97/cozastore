package com.cybersoft.cozastore.provider;

import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenManagerProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//       Lấy username người dùng truyền lên
        String username = authentication.getName();
//      Lấy mật khẩu người dùng truyền lên
        String password = authentication.getCredentials().toString();
//        Lấy thông tin user từ database thông qua username người dùng truyền vào
        UserEntity user = userRepository.findByUsername(username);
        if(user != null){
            //So sánh password người dùng truyền vào với password BCrypt lưu trong database
            if(passwordEncoder.matches(password,user.getPassword())){
                //Trùng password
                return new UsernamePasswordAuthenticationToken(username,user.getPassword(),new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        Kiểu hỗ trợ so sánh chứng thực cho AuthenticationManager
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
