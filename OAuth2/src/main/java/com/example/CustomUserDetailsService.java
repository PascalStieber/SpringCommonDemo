package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.persistence.User;
import com.example.persistence.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {



	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		if (user!=null) {
//			CustomUserDetails customUserDetails = new CustomUserDetails("john@jim.com", "a", authorities);
			return translateToUserDetails(user);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	private org.springframework.security.core.userdetails.User translateToUserDetails(User pPersistenceUser){
		//// @formatter:off
		org.springframework.security.core.userdetails.User userCredential = new org.springframework.security.core.userdetails.User(
				pPersistenceUser.getUsername(), 
				pPersistenceUser.getPassword(), 
				pPersistenceUser.isEnabled(),
				pPersistenceUser.isAccountNonExpired(), 
				pPersistenceUser.isCredentialsNonExpired(), 
				pPersistenceUser.isAccountNonLocked(), 
				getAuthorities(pPersistenceUser) 
				);
		// @formatter:on
		return userCredential;
	}
	
	 private List<GrantedAuthority> getAuthorities(User user) {
	        Set<String> roles = user.getRoles();
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (String role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role));
	        }
	        return authorities;
	}

}