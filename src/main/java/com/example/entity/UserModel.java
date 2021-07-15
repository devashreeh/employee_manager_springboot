package com.example.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = false)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private String userName;
	@NonNull	
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private String email;
	private String profileImageUrl;
	private String password;
	private boolean enabled;
	private String sessionToken;
	private String resetPasswordToken;
	private String userRole;
}
