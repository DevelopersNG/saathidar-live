package com.sathidar.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter{

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

//	private String principalRequestHeader;
//	
//	public APIKeyAuthFilter(String principalRequestHeader) {
//        this.principalRequestHeader = principalRequestHeader;
//    }
//	
//	@Override
//	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//		 return request.getHeader(principalRequestHeader);
//	}
//
//	@Override
//	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
//		 return "N/A";
//	}

}
