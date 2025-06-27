package com.example.demo.User;

import java.util.Optional;
import com.example.demo.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
	    SiteUser user = new SiteUser();
	    user.setUsername(username);
	    user.setEmail(email);
	    user.setPassword(passwordEncoder.encode(password));
	    user.setRole(UserRole.USER.getValue());  // 이 부분 추가 (예: "ROLE_USER")
	    this.userRepository.save(user);
	    return user;
	}

	
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser= this.userRepository.findByUsername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
