package dev.a2.springdemo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.a2.springdemo.model.User;
import dev.a2.springdemo.service.UserService;
import dev.a2.springdemo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
    private UserService userService;
	
	@ModelAttribute("user")
    public UserRegistrationDto registrationModel() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
    public String showRegistrationForm(Model model) {
		model.addAttribute("registration", new User());
        return "registration";
    }
	
	@PostMapping
    public String registerUserAccount(
    		@ModelAttribute("user") @Valid UserRegistrationDto userDto,
    		BindingResult result) {
		User existing = userService.findByEmail(userDto.getEmail());
		
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/login";
    }

}
