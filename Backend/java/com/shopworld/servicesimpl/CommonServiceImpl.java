package com.shopworld.servicesimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shopworld.services.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CommonServiceImpl implements CommonService{

	@Override
	public void removeSessionMessage() {
		HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session=request.getSession();
		session.removeAttribute("successMessage");
		session.removeAttribute("errorMessage");
	}

}
