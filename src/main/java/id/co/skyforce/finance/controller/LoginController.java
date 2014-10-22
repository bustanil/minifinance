package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.domain.User;
import id.co.skyforce.finance.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Transaction;
import org.hibernate.Session;


@ManagedBean
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
	
	
	public LoginController(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		
		if (id != null){
			Integer userId = Integer.valueOf(id);
			
			Session session = HibernateUtil.openSession();
			Transaction trx = session.beginTransaction();
			
			
			User user = (User) session.get(User.class, userId);
			
			this.id = user.getId();
			username = user.getUsername();
			password = user.getPassword();
			lastLogin = user.getLastLogin();
			
			trx.commit();
			session.close();
		}
		
	}
	
	public String saveLogin(){
		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();
		
		User user = new User();
		
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setLastLogin(lastLogin);
		
		session.save(user);
		trx.commit();
		session.close();
		
		this.message = "Login Success!!!";
		return "index";
	}
	
	
	

}
