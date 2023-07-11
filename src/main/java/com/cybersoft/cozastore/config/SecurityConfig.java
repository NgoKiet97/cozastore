package com.cybersoft.cozastore.config;

//import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.provider.CustomAuthenManagerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
//MD5, SHA1, RSA...

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CustomAuthenManagerProvider customAuthenManagerProvider;

//    @Autowired
//    JwtFilter jwtFilter;

    /**
     * Khai báo dạng mã hóa giành cho password
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    Tạo ra AuthenticationManager để custom lại thông tin chứng thực
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenManagerProvider).build();

    }

//    Custom lại thông tin cấu hình của spring security
    /**
     * Java 8, 11 : antMatchers
     * Java 17~ : requestMatcher
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Không sử dụng session
                .and()
                .authorizeHttpRequests()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/category/**").permitAll()
                    .antMatchers("/product/file/**").permitAll()
                    .antMatchers("/product/category/**").permitAll()
                    .antMatchers("/product/**").permitAll()
                //Tất cả các link còn lại điều phải chứng thực
                    .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
