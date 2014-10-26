package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionController {
	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public void unsetCurrentUser() {
		this.currentUser = null;
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
