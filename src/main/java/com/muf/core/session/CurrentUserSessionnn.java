//package com.muf.core.session;
//
//import com.muf.common.constant.AppConstant;
//import com.muf.common.context.AppContextService;
//import com.muf.common.model.UserSession;
//import com.muf.modules.authentication.token.entity.domain.Token;
//import com.muf.modules.authentication.token.service.TokenService;
//import com.muf.modules.user.entity.domain.User;
//import com.muf.modules.user.entity.domain.UserRoleService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CurrentUserSessionnn {
//
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    protected HttpServletRequest httpServletRequest;
//
//    @Autowired
//    protected TokenService tokenService;
//
////    @Autowired
////    protected TokenBeanService tokenBeanService;
//
////    @Autowired
////    protected UserRoleRepository userRoleRepository;
//
////    @Autowired
////    protected UserBeanRepository userBeanRepository;
//
////    @Autowired
////    protected VendorBeanRepository vendorBeanRepository;
//
//    public String getStrToken() {
//        try {
//            return httpServletRequest.getHeader(AppConstant.TOKEN_KEY);
//        } catch (Exception e) {
//            logger.debug("getUser: NULL");
//            return null;
//        }
//    }
//
//    public UserSession getUserSession() {
//
//        try {
//            String strToken = getStrToken();
//
//            if (strToken == null)
//            return null;
//
//            UserSession userSession = AppContextService.getActiveUserSession().get(strToken);
//
//            logger.debug("UserSession return = " + userSession);
//
//            if (userSession == null) {
//                logger.debug("UserSession reread from token table!, strToken = " + strToken);
//                Token token = tokenService.getValidByTokenString(strToken);
//
//                logger.debug("UserSession reread from token, token = " + token);
//                if (token != null) {
//                    User user = token.getUser();
//                    userSession = new UserSession();
//                    userSession.setUser(user);
////                    userSession.setUserBean(UserBeanRepository.getById(user.getId()));
//                    userSession.setCurrentLangCode(user.getLang().getCode());
//                    List<UserRoleService> userRoleList = userRoleRepository.getListByWhere(" WHERE e.user.id " + user.getId() + " and e.role.id = " + token.getRole().getId());
//                    userSession.setActiveUserRole(userRoleList.get(0));
//                    userSession.setVendor(userSession.getActiveUserRole().getCode().getCode().equals(AppConstant.ROLE_CODE_VENDOR)
//                            ? vendorBeanRepository.getByUserId(user.getId())
//                            : null);
//                    AppContextService.getActiveUserSession().put(strToken, userSession);
//                }
//            }
//
//            logger.debug("return userSession = " + userSession);
//            return userSession;
//
//        } catch (Exception e) {
//            logger.error("Catch error getUserSession: NULL");
//        }
//
//        return null;
//    }
//
//    public User getUser() {
//        try {
//            return getUserSession().getUser();
//        } catch (Exception e) {
//            logger.debug("getUser: NULL");
//        }
//
//        return null;
//    }
//
//    public Integer getUserId() {
//        try {
//            return getUserSession().getUser().getId();
//        } catch (Exception e) {
//            logger.debug("getUserId: NULL");
//        }
//        return null;
//    }
//
//    public String getLangCode() {
//        return getUserSession() == null ? null : getUserSession().getCurrentLangCode();
//    }
//
//    public UserRoleService getActiveUserRole() {
//        return getUserSession() == null ? null : getUserSession().getActiveUserRole();
//    }
//
//}
