package com.cybersoft.cozastore.filter;

import com.cybersoft.cozastore.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
//OncePerRequestFilter : Có request yêu cầu chứng thực thì chạy vào filter
public class JwtFilter extends OncePerRequestFilter {

//    Bước 1 : Lấy token
//    Bước 2 : Giải mã token
//    Bước 3 : Token hợp lệ tạo chứng thực cho Security

    @Autowired
    JWTHelperUtils jwtHelperUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = "";
        if(header != null){
            token = header.substring(7);
        }

        try{
            String data = jwtHelperUtils.validToken(token);
            if(!data.isEmpty()){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            System.out.println("token không hợp lệ");
        }
        filterChain.doFilter(request,response);
    }

}
