package com.cts.FuelDrop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cts.FuelDrop.Entity.User;
import com.cts.FuelDrop.Service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	public String getUserIdFromToken(String token) {
		DecodedJWT jwt = JWT.decode(token);
		String userId = jwt.getClaim("userid").asString();
		return userId;
	}

//	@GetMapping("/user/{userid}")
//	@PreAuthorize("#userid== authentication.principal.userid")
//	public ResponseEntity<?> getUserById(@PathVariable int userid) {
//		User user = userService.getUserById(userid);
//		return ResponseEntity.ok(user.getName() + "\n" + user.getPhoneno());
//	}

//	@GetMapping("/user/{userid}")
//	public ResponseEntity<?> getUserById(@PathVariable int userid) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		int authenticatedUserId = extractUserIdFromToken(authentication); // Implement this method
//
//		// Compare authenticatedUserId with the requested userid
//		if (authenticatedUserId != userid) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//		}
//
//		// Proceed with fetching user data
//		User user = userService.getUserById(userid);
//		return ResponseEntity.ok(user.getName() + "\n" + user.getPhoneno());
//	}

}