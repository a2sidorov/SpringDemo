package dev.a2.springdemo.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	

	// GET login page
   	@GetMapping(path="/login")
   	public String showLoginPage() {
   		return "login";
    }
	
	// GET home page
 	@GetMapping(path="/")
 	public String showHomePage() {
         return "home";
     }
 	
  	// GET hello page
  	@GetMapping(path="/profile")
  	public String showProfilePage(Model model) {
  		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
  		model.addAttribute("email", email);
        return "profile";
     }
  	
  	
 	
 	/*
 	// POST login page
 	@PostMapping(path="/login")
 	public void loginPost(@RequestBody Map<String, String> data) throws Exception {
 		System.out.println(data);
 	}

    @Autowired
    ItemController itemService;

    public static List<Item> items;
    static{
        items = new ArrayList<>(Arrays.asList(new Item(1,"Spring Boot in Action","Books"),
                new Item(2,"Java 8 in Action","Books"),
                new Item(3,"Data Structures","Books"),
                new Item(4,"Spring Boot Security","Books")));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllItems")
    @ResponseBody
    public ResponseEntity<List<Item>> getAllItems() {
        //Reading all items (ADMIN only can access this)
        List<Item> items = this.items;
        System.out.println("Reading items: "+items);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }
    */

    

}
