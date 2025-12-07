package com.dongyang.firstProject.service;

import com.dongyang.firstProject.config.PrincipalDetails;
import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 찾을 수 없습니다: " + username));
        return new PrincipalDetails(userEntity);
    }
}