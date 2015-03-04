package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.MailService;
import com.premium.spirit.society.core.businessLayer.service.UserService;
import com.premium.spirit.society.core.dataLayer.DAO.RoleDAO;
import com.premium.spirit.society.core.dataLayer.DAO.UserDAO;
import com.premium.spirit.society.core.dataLayer.entity.RoleEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<UserFormBO, UserEntity> implements UserService {

    private RoleDAO roleDAO;

    private MailService mailService;

    private UserDAO userDAO;

    private Mapper dozer;

    @Autowired
    public UserServiceImpl(RoleDAO roleDAO,
                           MailService mailService,
                           UserDAO userDAO,
                           Mapper dozer) {

        this.roleDAO = roleDAO;
        this.mailService = mailService;
        this.userDAO = userDAO;
        this.dozer = dozer;
    }

    @Override
    @Transactional
    public UserFormBO getUserByUsername(String username) {
        UserEntity userEntity = userDAO.findUserByUsername(username);
        if (userEntity == null)
            return null;
        UserFormBO userFormBO = dozer.map(userEntity, UserFormBO.class);
        return userFormBO;
    }

    @Override
    @Transactional
    public UserFormBO getUserByEmail(String email) {
        UserEntity userEntity = userDAO.findUserByEmail(email);
        if (userEntity == null)
            return null;
        UserFormBO userFormBO = dozer.map(userEntity, UserFormBO.class);
        return userFormBO;
    }

    @Override
    @Transactional
    public int create(UserFormBO user, Locale locale) {

        String password = RandomStringUtils.randomAlphanumeric(10);
        user.setPassword(DigestUtils.sha256Hex(password + "{" + user.getUsername() + "}"));

        UserEntity userEntity = dozer.map(user, UserEntity.class);
        /* assigning roles to user */
        ArrayList<RoleEntity> roles = new ArrayList<>();
        roles.add(roleDAO.getById(1, RoleEntity.class));
        userEntity.setRoles(roles);
        int userId = userDAO.saveUser(userEntity);

		/* sending email to user about account creation */
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("subject", "creationOfANewUser");
            map.put("user", user);
            map.put("password", password);
            mailService.sendMail(user.getContact().getEmail(), map, locale);

        } catch (Exception e) {
//
        }
        return userId;
    }

    @Override
    @Transactional
    public boolean isUniqueUsername(UserFormBO user) {
        UserFormBO tmpUser = getUserByUsername(user.getUsername());
        if (tmpUser==null)
            return true;
        return user.getId()==tmpUser.getId();
    }

    @Override
    @Transactional
    public boolean isUniqueEmail(UserFormBO user) {
        UserFormBO tmpUser = getUserByEmail(user.getContact().getEmail());
        if (tmpUser==null)
            return true;
        return user.getId()==tmpUser.getId();
    }

    @Override
    @Transactional
    public boolean isUniqueUsername(int userId, String username) {
        UserFormBO user = getById(userId, UserFormBO.class, UserEntity.class);

        return user.getUsername().equals(username);
    }

    @Override
    @Transactional
    public boolean isUniqueEmail(int userId, String email) {
        UserFormBO user = getUserByEmail(email);
        return user.getContact().getEmail().equals(email);
    }

    @Override
    @Transactional
    public List<UserDisplayBO> findAllNonHidden() {

        List<UserEntity> userEntityList = userDAO.findAllNonHidden();
        List<UserDisplayBO> userDisplayBOList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList) {
            userDisplayBOList.add(dozer.map(userEntity, UserDisplayBO.class));
        }

        return userDisplayBOList;
    }

    @Override
    @Transactional
    public void changePassword(UserFormBO user) {

        user.setPassword(DigestUtils.sha256Hex(user.getPassword()
                + "{" + user.getUsername() + "}"));
        new ShaPasswordEncoder(256).encodePassword(user.getPassword(), user.getUsername());
        UserEntity userEntity = dozer.map(user, UserEntity.class);
        System.out.println(userEntity.getPassword());
        userDAO.update(userEntity);
    }

    @Override
    @Transactional
    public void hide(int userId) {
        userDAO.hide(userId);
    }

    @Override
    @Transactional
    public void revokeRoles(int userId) {
        UserEntity user = genericDAO.getById(userId, UserEntity.class);
        user.setRoles(new ArrayList<RoleEntity>());
        genericDAO.update(user);
    }

    @Override
    @Transactional
    public void restoreUser(int userId) {
        UserEntity user = genericDAO.getById(userId, UserEntity.class);
        List<RoleEntity> roleList = new ArrayList<>();
        roleList.add(roleDAO.getById(1, RoleEntity.class));
        user.setRoles(roleList);
        user.setEnabled(true);
        user.setHidden(false);
        genericDAO.update(user);
    }

    @Override
    @Transactional
    public int getLoggedUserId() {

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();

        UserEntity user = dozer.map(this.getUserByUsername(name), UserEntity.class);
        return user.getId();
    }

    @Override
    @Transactional
    public int getCountOfUnhiddenCustomers(String searchString) {
        return userDAO.getCountOfUnhiddenCustomers(searchString);
    }

    @Override
    @Transactional
    public List<UserDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        List<UserEntity> userList = userDAO.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        List<UserDisplayBO> userDisplayVoList = new ArrayList<>();
        for (UserEntity customer : userList) {
            UserDisplayBO userDisplayBO = dozer.map(customer, UserDisplayBO.class);
            userDisplayVoList.add(userDisplayBO);
        }
        return userDisplayVoList;
    }
}
