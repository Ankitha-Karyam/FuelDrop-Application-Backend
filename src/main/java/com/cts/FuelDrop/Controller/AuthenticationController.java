package com.cts.FuelDrop.Controller;

import java.util.HashMap;
import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.FuelDrop.Entity.User;
import com.cts.FuelDrop.Repository.UserRepository;
import com.cts.FuelDrop.Service.UserService;

@RestController
@RequestMapping("auth/v1")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
	    try {
	        boolean isAuthenticated = userService.authenticate(
	            user.getPhoneno(),
	            user.getPassword(),
	            user.getUsertype()
	        );

	        Map<String, String> response = new HashMap<>();
	        if (isAuthenticated) {
	            // Generate and return the token or login success response
	            response.put("message", "Login successful!");
	            response.put("phonenumber",user.getPhoneno());
	            response.put("usertype", user.getUsertype());
	            User udetails = userRepo.findByPhoneno(user.getPhoneno()).orElse(null);
	            response.put("username", udetails.getName());
	            return ResponseEntity.ok().body(response);
	        } else {
	            // Return a login failure response
	            response.put("message", "Invalid credentials!");
	            return ResponseEntity.ok().body(response);
	        }
	    } catch (Exception e) {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "An error occurred during login.");
	        return ResponseEntity.status(500).body(response);
	    }
	}

	
	

	

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		user.setUsertype("customer");
		if (userService.addUser(user) != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("user registration failed", HttpStatus.INTERNAL_SERVER_ERROR);

	}

//	public String generateToken(String phoneno, String password, String userType) throws ServletException, Exception {
//		String jwtToken;
//		if (phoneno == null || password == null) {
//			throw new ServletException("Please enter valid credentials");
//		}
//
//		boolean flag = userService.authenticate(phoneno, password, userType);
//
//		if (!flag) {
//			throw new ServletException("Invalid credentials");
//		}
//
//		else {
//			jwtToken = Jwts.builder().setSubject(phoneno).setIssuedAt(new Date())
//					.setExpiration(new Date(System.currentTimeMillis() + 3000000))
//					.signWith(SignatureAlgorithm.HS256, "secret key").compact();
//
//		}
//
//		return jwtToken;
//
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> loginn(@RequestBody User user) {
//
//		try {
//			String jwtToken = generateToken(user.getPhoneno(), user.getPassword(), user.getUsertype());
//			mapObj.put("Message", "User successfully logged in");
//			mapObj.put("Token", jwtToken);
//			mapObj.put("userType", user.getUsertype());
//			mapObj.put("userid", Integer.toString(user.getUserid()));
//		} catch (Exception e) {
//			mapObj.put("Message", "User not logged in");
//			mapObj.put("Token", null);
//			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
//		}
//
//		return new ResponseEntity<>(mapObj, HttpStatus.OK);
//	}

	@GetMapping("/isNumberExist/{phoneno}")
	public ResponseEntity<Boolean> isNumberExist(@PathVariable String phoneno) {
		boolean exists = userService.isNumberExist(phoneno);
		return ResponseEntity.ok(exists);
	}

}
