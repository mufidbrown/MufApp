package com.muf.model;

import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {

    private User user;
//    private UserBean userBean;
    private String currentLangCode;
    private UserRole activeUserRole;
//    private MenuBean activeMenu;
//    private VendorBean vendor;
//    private OrganizationBean organizationBean;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentLangCode() {
        return currentLangCode;
    }

    public void setCurrentLangCode(String currentLangCode) {
        this.currentLangCode = currentLangCode;
    }

    public UserRole getActiveUserRole() {
        return activeUserRole;
    }

    public void setActiveUserRole(UserRole activeUserRole) {
        this.activeUserRole = activeUserRole;
    }
}
