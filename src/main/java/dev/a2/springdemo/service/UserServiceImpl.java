package dev.a2.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.a2.springdemo.model.Role;
import dev.a2.springdemo.model.User;
import dev.a2.springdemo.repos.UsersRepository;
import dev.a2.springdemo.service.UserServiceImpl;
import dev.a2.springdemo.web.dto.UserRegistrationDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    
    @Override
	public User findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
    
    @Override
    public User save(UserRegistrationDto userDto){
    	User newUser = new User();
    	newUser.setEmail(userDto.getEmail());
    	newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	newUser.setIsActive(1);
    	Set<Role> set = new HashSet<>();
    	Role role = new Role();
    	role.setRoleName("USER");
    	set.add(role);
        newUser.setRoles(set);
        return usersRepository.save(newUser);
    }
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

	
    
}
