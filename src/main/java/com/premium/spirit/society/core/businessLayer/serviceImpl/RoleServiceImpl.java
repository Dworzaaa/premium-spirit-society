package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.RoleFormBO;
import com.premium.spirit.society.core.businessLayer.service.RoleService;
import com.premium.spirit.society.core.dataLayer.DAO.UserDAO;
import com.premium.spirit.society.core.dataLayer.entity.RoleEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class RoleServiceImpl.
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<RoleFormBO, RoleEntity> implements RoleService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<UserDisplayBO> getAllDoctors() {
        RoleEntity doctorRole = genericDAO.getById(3, RoleEntity.class);
        List<UserEntity> doctorEntityList = doctorRole.getUsers();
        List<UserDisplayBO> doctorVoList = new ArrayList<>();
        for (UserEntity doctorEntity : doctorEntityList) {
            UserDisplayBO doctorVo = dozer.map(doctorEntity, UserDisplayBO.class);
            doctorVoList.add(doctorVo);
        }
        return doctorVoList;
    }

    @Override
    @Transactional
    public List<RoleFormBO> getPossibleRoles(int userId) {
        UserEntity user = userDAO.getById(userId, UserEntity.class);


        List<RoleFormBO> possibleRoles = getAll(RoleFormBO.class, RoleEntity.class);
        Iterator<RoleFormBO> i = possibleRoles.iterator();
        while (i.hasNext()) {
            RoleFormBO role = i.next();
            for (RoleEntity roleEnt : user.getRoles()) {
                if (roleEnt.getId() == role.getId()) {
                    i.remove();
                }
            }
        }

        //Collections.sort(possibleRoles);
        return possibleRoles;
    }
}
