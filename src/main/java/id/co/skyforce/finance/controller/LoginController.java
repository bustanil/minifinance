
package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.service.LoginService;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class LoginController{

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


	public String checkLogin(){
			boolean result = LoginService.loginn(username, password, user);
			if(result == true) {
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setLastLogin(new Date());
				
				LoginService loginService = new LoginService();
				loginService.loginn(username, password, user);
				return "mainblank.xhtml?faces-redirect=true";
			} else{
				FacesContext.getCurrentInstance().addMessage(
		                    null,
		                    new FacesMessage(FacesMessage.SEVERITY_WARN,
		                    "Invalid Login!",
		                    "Please Try Again!"));
		 
		            // invalidate session, and redirect to other pages
		 
		            //message = "Invalid Login. Please Try Again!";
		            return "index.xhtml?faces-redirect=true";
			}
		}

}
