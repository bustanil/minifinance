
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

//	public LoginController() {
//		String id = FacesContext.getCurrentInstance().getExternalContext()
//				.getRequestParameterMap().get("id");
//
//		if (id != null) {
//			Integer userId = Integer.valueOf(id);
//
//			Session session = HibernateUtil.openSession();
//			Transaction trx = session.beginTransaction();
//			
//			User user = (User) session.get(User.class, userId);
//			this.id = user.getId();
//			this.username = user.getUsername();
//			this.password = user.getPassword();
//			this.lastLogin = user.getLastLogin();
//			
//			trx.commit();
//			session.close();
//		}
//
//	}

	public String checkLogin(){
		
		user.setLastLogin(new Date());
		
		LoginService loginService = new LoginService();
		loginService.login(user);
		

		return "index?faces-redirect=true";
	}

}
