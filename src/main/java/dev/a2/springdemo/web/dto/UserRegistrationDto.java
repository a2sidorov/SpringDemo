package dev.a2.springdemo.web.dto;

import javax.validation.constraints.Size;

import dev.a2.springdemo.validation.FieldMatch;
import dev.a2.springdemo.validation.ValidEmail;


@FieldMatch(field = "password", fieldMatch = "confirmPassword")
public class UserRegistrationDto {
	
	@ValidEmail
    private String email;
	
    @Size(min=6, message = "Password must be at least 6 characters long" )
    @Size(max=30, message = "Password must be less than 30 characters long" )
    private String password;
	
	
    private String confirmPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
    
    

}
