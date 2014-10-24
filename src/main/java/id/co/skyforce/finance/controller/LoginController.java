package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.service.LoginService;
import id.co.skyforce.finance.util.HibernateUtil;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@SessionScoped
public class LoginController {

	private Integer id;
	private String username;
	private String password;
	private Date lastLogin;

	private String message;

	private User user = new User();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void checkLogin(){

//				user.setLastLogin(new Date());
				
				
//			user.setId(id);
//			user.setLastLogin(new Date());
//			user.setPassword(password);
//			user.setUsername(username);
			LoginService loginService = new LoginService();
//			loginService.login(user);
//			loginService.loginDifferentUser(user);
			
			if (user.getId() != null){
				user.setLastLogin(new Date());
				loginService.login(user);
			}
			else if (user.getId() == null){
				user.setId(id);
				user.setLastLogin(new Date());
				user.setPassword(password);
				user.setUsername(username);
				loginService.loginDifferentUser(user);
			}
				
	}
	
	
	
	
	

}
