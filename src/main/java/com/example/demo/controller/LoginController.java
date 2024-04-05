package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ResourceLeave;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Timesheet;
import com.example.demo.Entity.User;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TimesheetRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private LeaveRepository resourceLeaveRepository;
	@Autowired
	private TimesheetRepository timesheetRepository;

	@PostMapping("/login")

	public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
		User storedUser = userRepository.findByUserLoginId(user.getUserLoginId(), user.getPassword());
		if (storedUser.getUserLoginId().equals(user.getUserLoginId())
				&& storedUser.getPassword().equals(user.getPassword())) {
			// Get role description associated with the user
			String roleDescription = getRoleDescription(storedUser);

			// Set session attribute to indicate user authentication status
			request.getSession().setAttribute("loggedInUserId", storedUser.getUserId());
			request.getSession().setAttribute("authenticated", true);

			// Return login successful along with role description
			return ResponseEntity.ok("Login successful. Role: " + roleDescription);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@PostMapping("/upload-leave")
    public ResponseEntity<String> uploadLeaveDetails(@RequestBody List<ResourceLeave> resourceLeaveList,
                                                      HttpServletRequest request) {
        // Check if user is authenticated
        Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
        if (isAuthenticated == null || !isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Get the logged-in user
        User loggedInUser = getCurrentUser(request);

        // Check if the logged-in user has the admin role
        if (isAdmin(loggedInUser)) {
            // Allow leave upload for admin user
            for (ResourceLeave resourceLeave : resourceLeaveList) {
                ResourceLeave savedLeave = resourceLeaveRepository.save(resourceLeave);
            }
            return ResponseEntity.ok("Leave details uploaded successfully");
        } else {
            // Deny leave upload for non-admin users
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only admin users can upload leave details");
        }
    }
	
	
	
	@PostMapping("/upload-timesheet")
    public ResponseEntity<String> uploadTimesheetDetails(@RequestBody List<Timesheet> resourceLeaveList,
                                                      HttpServletRequest request) {
        // Check if user is authenticated
        Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
        if (isAuthenticated == null || !isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Get the logged-in user
        User loggedInUser = getCurrentUser(request);

        // Check if the logged-in user has the admin role
        if (isAdmin(loggedInUser)) {
            // Allow leave upload for admin user
            for (Timesheet resourceLeave : resourceLeaveList) {
            	Timesheet savedLeave = timesheetRepository.save(resourceLeave);
            }
            return ResponseEntity.ok("Timesheet details uploaded successfully");
        } else {
            // Deny leave upload for non-admin users
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only admin users can upload timesheet details");
        }
    }

	// Method to get the current logged-in user
	private User getCurrentUser(HttpServletRequest request) {
		// Get the logged-in user ID from the session
		Long loggedInUserId = (Long) request.getSession().getAttribute("loggedInUserId");

		if (loggedInUserId != null) {
			// Fetch the user from the database using the user ID
			return userRepository.findById(loggedInUserId).orElse(null);
		} else {
			// No user logged in, return null or handle as appropriate
			return null;
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Don't create a new session if none exists
	    if (session != null) {
	        session.invalidate(); // Invalidate the session
	    }
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Logout successful");
	    return ResponseEntity.ok(response);
	}

	private final ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/fetch/leaveMonth/{leaveMonth}")
	public ResponseEntity<?> fetchLeaveReport(@PathVariable String leaveMonth, HttpServletRequest request) {
		// Check if user is authenticated
		Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
		if (isAuthenticated == null || !isAuthenticated) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		// Get the logged-in user
		User loggedInUser = getCurrentUser(request);

		// Check if the logged-in user has the admin role
		if (isAdmin(loggedInUser)) {
			// Allow leave report fetch for admin user
			List<ResourceLeave> leaveReport = resourceLeaveRepository.findByLeaveMonth(leaveMonth);
			if (leaveReport != null && !leaveReport.isEmpty()) {
				return ResponseEntity.ok(leaveReport);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No leave records found for the specified month");
			}
		} else {
			// Deny leave report fetch for non-admin users
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can fetch leave report");
		}
	}
	
	
	@GetMapping("/Timesheets/leaveMonth/{leaveMonth}")
	public ResponseEntity<?> fetchTimesheetReport(@PathVariable String leaveMonth, HttpServletRequest request) {
		// Check if user is authenticated
		Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
		if (isAuthenticated == null || !isAuthenticated) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		// Get the logged-in user
		User loggedInUser = getCurrentUser(request);

		// Check if the logged-in user has the admin role
		if (isAdmin(loggedInUser)) {
			// Allow leave report fetch for admin user
			List<Timesheet> leaveReport = timesheetRepository.findByTimesheetMonth(leaveMonth);
			if (leaveReport != null && !leaveReport.isEmpty()) {
				return ResponseEntity.ok(leaveReport);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No Timesheet records found for the specified month");
			}
		} else {
			// Deny leave report fetch for non-admin users
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can fetch leave report");
		}
	}

	@GetMapping("/fetched/leaveMonth/{leaveMonth}")
	public ResponseEntity<?> fetchResourceLeaveDataByMonth(@PathVariable String leaveMonth,
			HttpServletRequest request) {
		// Check if user is authenticated
		Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
		if (isAuthenticated == null || !isAuthenticated) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		// Get the logged-in user
		User loggedInUser = getCurrentUser(request);

		// Check if the logged-in user has the "user" role
		if (hasUserRole(loggedInUser, "userrole")) {
			// Allow fetching resource leave data for users with "user" role
			List<ResourceLeave> resourceLeaveData = resourceLeaveRepository.findByLeaveMonth(leaveMonth);
			if (resourceLeaveData != null && !resourceLeaveData.isEmpty()) {
				return ResponseEntity.ok(resourceLeaveData);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No resource leave data found for the specified month");
			}
		} else {
			// Deny fetching resource leave data for users without the "user" role
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Only users with 'userrole' role can fetch resource leave data");
		}
	}
	
	
	
	@GetMapping("/Timesheet/leaveMonth/{leaveMonth}")
	public ResponseEntity<?> fetchTimesheetLeaveDataByMonth(@PathVariable String leaveMonth,
			HttpServletRequest request) {
		// Check if user is authenticated
		Boolean isAuthenticated = (Boolean) request.getSession().getAttribute("authenticated");
		if (isAuthenticated == null || !isAuthenticated) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		// Get the logged-in user
		User loggedInUser = getCurrentUser(request);

		// Check if the logged-in user has the "user" role
		if (hasUserRole(loggedInUser, "userrole")) {
			// Allow fetching resource leave data for users with "user" role
			List<Timesheet> resourceLeaveData = timesheetRepository.findByTimesheetLeaveMonth(leaveMonth);
			if (resourceLeaveData != null && !resourceLeaveData.isEmpty()) {
				return ResponseEntity.ok(resourceLeaveData);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No resource leave data found for the specified month");
			}
		} else {
			// Deny fetching resource leave data for users without the "user" role
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Only users with 'userrole' role can fetch resource leave data");
		}
	}

	// Method to check if the user has a specific role
	private boolean hasUserRole(User user, String role) {
		List<Role> roles = user.getRoles();
		for (Role userRole : roles) {
			if (role.equals(userRole.getRoleDescription())) {
				return true;
			}
		}
		return false;
	}

	// Method to get the role description associated with the user
	private String getRoleDescription(User user) {
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			return role.getRoleDescription(); // Assuming a user has only one role
		}
		return "No role assigned"; // Default message if no role is found
	}

	// Method to check if the user has the "admin" role
	private boolean isAdmin(User user) {
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			if ("admin".equals(role.getRoleDescription())) {
				return true;
			}
		}
		return false;
	}
}
