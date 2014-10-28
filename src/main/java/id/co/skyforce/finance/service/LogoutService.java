package id.co.skyforce.finance.service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutService {
	
	public static HttpSession getSession(){
		return (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest)
				FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static String getUsername(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

}
