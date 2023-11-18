package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserActivityController {
	
	private final static List<User> users = new ArrayList<>();
	
	@PostMapping("")
	public User addUser(@RequestBody User user) {
		users.add(user);
		return user;
	}
	
	@GetMapping("")
	public List<User> getUsers() {
		return users;
	}
	
	@GetMapping("/{name}")
	public User getUsersByName(@PathVariable("name") String name) {
		return users.stream().filter(user -> user.getUsername().equals(name)).findFirst().get();
	}

	@DeleteMapping("/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		users.stream().filter(user -> user.getUsername().equals(username))
		.findAny()
		.ifPresent(users::remove);
		return "User has been deleted: " + username;
	}
	
	@PutMapping("/{username}")
	public Post updateUser(@PathVariable("username") String username, @RequestBody Post post) {
		users.stream().filter(user -> user.getUsername().equals(username))
		.findAny()
		.ifPresent(user->user.getPosts().add(post));
		return post;
	}
}
