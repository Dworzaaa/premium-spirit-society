package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.AuthorizationChecker;
import com.premium.spirit.society.core.businessLayer.service.UserRoleService;
import com.premium.spirit.society.core.businessLayer.service.UserService;
import com.premium.spirit.society.core.dataLayer.entity.RoleEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserRoleEntity;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AuthorizationCheckerImpl implements AuthorizationChecker {

    @Autowired
    private Mapper dozer;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    public boolean checkAuthorization(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        boolean isAuthorized = false;
        UserFormBO user = userService.getUserByUsername(name);
        if (user == null)
            return isAuthorized;

        List<UserRoleEntity> roles = userRoleService.findAllUserRolesByUserID((userService.getUserByUsername(name)).getId());

        for (UserRoleEntity r : roles) {
            if (r.getRoleId() == (1)) {
                isAuthorized = true;
                break;
            }
        }
        if (!isAuthorized) {
            // SecurityContextHolder.getContext().setAuthentication(null);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return isAuthorized;
    }

    public boolean onlyResearcher() {
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UserFormBO userFormBO = userService.getUserByUsername(name);
        if (userFormBO == null)
            return false;
        List<RoleEntity> roles = dozer.map(userFormBO, UserEntity.class).getRoles();

        return (roles.size() == 2) && ((roles.get(0).getId() == 1 && roles.get(1).getId() == 2) || ((roles.get(0).getId() == 2 && roles.get(1).getId() == 1)));
    }

    @Override
    @Transactional
    public boolean isAdmin() {
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UserFormBO userFormBO = userService.getUserByUsername(name);
        if (userFormBO == null)
            return false;
        List<RoleEntity> roles = dozer.map(userFormBO, UserEntity.class).getRoles();

        for (RoleEntity role : roles) {
            if (role.getId() == 5) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean isUserFromUrl(String username) {
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UserFormBO user = userService.getUserByUsername(name);

        return user.getUsername() == username;
    }

    @Override
    @Transactional
    public boolean isSuperDoctor() {
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserFormBO userFormBO = userService.getUserByUsername(name);
        if (userFormBO == null)
            return false;
        List<RoleEntity> roles = dozer.map(userFormBO, UserEntity.class).getRoles();

        for (RoleEntity role : roles) {
            if (role.getId() == 4) {
                return true;
            }
        }
        return false;
    }
}
