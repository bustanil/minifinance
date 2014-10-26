package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionController {
	private User currentUser;

	public User getUser() {
		return currentUser;
	}

	public void setUser(User user) {
		this.currentUser = user;
	}

	public boolean isLoggedIn() {
		if (this.currentUser != null) {
			if (this.currentUser.getId() != null) {
				return true;
			}
		}

		return false;
	}

	public boolean isLoggedOut() {
		return !this.isLoggedIn();
	}
}
