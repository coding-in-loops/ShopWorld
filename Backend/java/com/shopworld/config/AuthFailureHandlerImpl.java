package com.shopworld.config;

import java.io.IOException;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.shopworld.entities.UserDtls;
import com.shopworld.repositories.UserRepository;
import com.shopworld.services.UserService;
import com.shopworld.utils.AppConstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler{

	private UserRepository userRepository;
	private UserService userService;
	
	public AuthFailureHandlerImpl(UserRepository userRepository, UserService userService) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
	}



	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String email=request.getParameter("username");
		UserDtls userDtls=userRepository.findByEmail(email);
	   if(userDtls!=null) {
		if(userDtls.getIsEnable()) {
			if(userDtls.getAccountNonLocked()) {
				if(userDtls.getFailedAttempt()< AppConstant.ATTEMPT_TIME) {
					userService.increaseFailedAttempt(userDtls);
				}else {
					userService.userAccountLock(userDtls);
					exception=new LockedException("Your account is locked, Failed attempt 3!");
				}
				
			}else {
				if(userService.unlockAccountTimeExpired(userDtls)) {
					exception=new LockedException("Your account is unlocked, Please try to login!");
				}else {
				exception=new LockedException("Your account is locked, Please try after sometime!");
				}
			}
		}else {
			exception=new LockedException("Your account is inactive!");
		}
	}else {
		exception=new LockedException("Email & Password is invalid!");
	}
		super.setDefaultFailureUrl("/signin?error");
		super.onAuthenticationFailure(request, response, exception);
	}

	
}
