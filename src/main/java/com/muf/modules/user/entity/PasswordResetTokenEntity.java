//package com.muf.modules.user.entity;
//
//
//import com.muf.base.entity.BaseEntity;
//import com.muf.modules.user.entity.domain.User;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.MappedSuperclass;
//import jakarta.persistence.OneToOne;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@MappedSuperclass
//public class PasswordResetTokenEntity extends BaseEntity implements Serializable {
//
//    private String token;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    private LocalDateTime expiryDate;
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public LocalDateTime getExpiryDate() {
//        return expiryDate;
//    }
//
//    public void setExpiryDate(LocalDateTime expiryDate) {
//        this.expiryDate = expiryDate;
//    }
//}
