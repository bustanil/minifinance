package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.LoginModel;
import id.co.skyforce.finance.model.domain.User;
import id.co.skyforce.finance.util.HibernateUtil;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class LoginController {

	private Integer id;
	private String username;
	private String password;
	private Date lastLogin;

	private String message;

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

	public LoginController() {
			
			User user;

			LoginModel loginModel = new LoginModel();
			user = loginModel.checkLogin(username, password);
			
			
			

	}

}
