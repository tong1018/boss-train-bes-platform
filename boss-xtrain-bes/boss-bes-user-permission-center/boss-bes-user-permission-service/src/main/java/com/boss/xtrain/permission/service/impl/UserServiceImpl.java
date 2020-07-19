package com.boss.xtrain.permission.service.impl;

import com.boss.xtrain.common.core.exception.BusinessException;
import com.boss.xtrain.common.core.exception.error.BusinessError;
import com.boss.xtrain.common.util.IdWorker;
import com.boss.xtrain.common.util.PojoUtils;
import com.boss.xtrain.permission.dao.*;
import com.boss.xtrain.permission.pojo.dto.ResourceDTO;
import com.boss.xtrain.permission.pojo.dto.RoleDTO;
import com.boss.xtrain.permission.pojo.dto.UserDTO;
import com.boss.xtrain.permission.pojo.dto.UserRoleDTO;
import com.boss.xtrain.permission.pojo.entity.User;
import com.boss.xtrain.permission.pojo.query.UserQueryDTO;
import com.boss.xtrain.permission.service.UserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author  :yushiqian
 * @Date    :16:43 2020/07/09
 * @Description :user service实现
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserSerivce {

    @Autowired
    UserDao userDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    PositionDao positionDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    RoleDao roleDao;

    private IdWorker worker = new IdWorker();


    private boolean isInUse(UserDTO dto){
        return dto.getStatus() == 1;
    }
    @Override
    public List<RoleDTO> getRoleByUserId(Long id) {
        return PojoUtils.copyListProperties(userDao.getRoleByUserId(id),RoleDTO::new);
    }

    @Override
    public UserDTO select(UserQueryDTO query) {
        try {
            UserDTO userDTO = userDao.queryByCondition(query).get(0);
            userDTO.setOrganizationId(companyDao.selectByKey(userDTO.getCompanyId()).getOrganizationId());
            userDTO.setOrganizationName(organizationDao.selectByPrimaryKey(userDTO.getOrganizationId()).getName());
            userDTO.setCompanyName(companyDao.selectByKey(userDTO.getCompanyId()).getName());
            userDTO.setDepartmentName(departmentDao.selectByKey(userDTO.getDepartmentId()).getName());
            return userDTO;
        }catch (Exception e){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_QUERY_ERROR,e);
        }
    }

    @Override
    public List<RoleDTO> getAllRoles(UserQueryDTO queryDTO) {
        return PojoUtils.copyListProperties(userDao.getAllRoles(queryDTO),RoleDTO::new);
    }

    @Override
    public List<ResourceDTO> getAllResource(UserQueryDTO queryDTO) {
        List<RoleDTO> roleDTOS = getAllRoles(queryDTO);
        List<ResourceDTO> resourceDTOS = new ArrayList<>();
        for(RoleDTO roleDTO : roleDTOS){
            resourceDTOS.addAll(PojoUtils.copyListProperties(roleDao.getResourcesByRoleId(roleDTO.getId()),ResourceDTO::new));
        }
        return resourceDTOS;
    }

    @Override
    public int deleteUserRole(List<UserRoleDTO> userRoleDTOS) {
        List<Long> ids = new ArrayList<>();
        for(UserRoleDTO dto : userRoleDTOS){
            ids.add(dto.getRoleId());
        }
        return userDao.deleteUserRole(ids);
    }

    @Override
    public boolean allocateRole(List<UserRoleDTO> dtos) {
        deleteUserRole(dtos);
        if(dtos.get(0).getRoleId() == null){
            return false;
        }else {
            try {
                for(UserRoleDTO userRoleDTO :dtos){
                    userRoleDTO.setId(worker.nextId());
                    userDao.allocateRole(userRoleDTO);
                }
            }catch (Exception e){
                log.error(e.getMessage());
                throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_ALLOCATE_ROLE_ERROR,e);
            }
            return true;
        }
    }

    @Override
    public User getStatusById(Long id) {
        return userDao.getStatusById(id);
    }

    @Override
    public List<UserDTO> selectByCondition(UserQueryDTO query) {
        try {
            List<UserDTO> userDTOS = userDao.queryByCondition(query);
            for(UserDTO userDTO : userDTOS){
                userDTO.setOrganizationId(companyDao.selectByKey(userDTO.getCompanyId()).getOrganizationId());
                userDTO.setOrganizationName(organizationDao.selectByPrimaryKey(userDTO.getOrganizationId()).getName());
                userDTO.setCompanyName(companyDao.selectByKey(userDTO.getCompanyId()).getName());
                userDTO.setDepartmentName(departmentDao.selectByKey(userDTO.getDepartmentId()).getName());
            }
            return userDTOS;
        }catch (Exception e){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_QUERY_ERROR);
        }
    }

    @Override
    public List<UserDTO> selectAll() {
        try {
            List<User> resources = userDao.selectAll();
            List<UserDTO> userDTOS = PojoUtils.copyListProperties(resources,UserDTO::new);
            for(UserDTO userDTO : userDTOS){
                userDTO.setOrganizationId(companyDao.selectByKey(userDTO.getCompanyId()).getOrganizationId());
                userDTO.setOrganizationName(organizationDao.selectByPrimaryKey(userDTO.getOrganizationId()).getName());
                userDTO.setCompanyName(companyDao.selectByKey(userDTO.getCompanyId()).getName());
                userDTO.setDepartmentName(departmentDao.selectByKey(userDTO.getDepartmentId()).getName());
//                userDTO.setPositionName(positionDao.selectByKey(userDTO.getPositionId()).getName());
            }
            return userDTOS;
        }catch (Exception e){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_QUERY_ERROR);
        }
    }

    @Override
    public int delete(UserDTO dto) {
        if(isInUse(dto)){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_IN_USE);
        }
        try {
            return userDao.delete(dto);
        }catch (Exception e){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_DELETE_ERROR,e);
        }
    }

    @Override
    public int delete(List<UserDTO> dtoList) {
        List<Long> ids = new ArrayList<>();
        for(UserDTO dto : dtoList){
            if(isInUse(dto))
                throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_IN_USE);
            ids.add(dto.getId());
        }
        try {
            return userDao.deleteByIds(ids);
        }catch (Exception e){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_DELETE_ERROR,e);
        }
    }

    @Override
    public int update(UserDTO dto) {
        if(!userDao.isExist(dto.getId())){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_NOT_EXIST_ERROR);
        }
        try {
            log.info(dto.toString());
//            return userDao.update(dto);
            return userDao.userUpdate(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_UPDATE_ERROR);
        }
    }

    @Override
    public int insert(UserDTO dto) {
        log.info(dto.toString());
        UserQueryDTO query = new UserQueryDTO();
        PojoUtils.copyProperties(dto,query);
        log.info(query.toString());
        List<UserDTO> list = userDao.queryByCondition(query);
        log.info(list.toString());
        if(!list.isEmpty()){
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_REPEAT_ERROR);
        }
        try {
            dto.setId(worker.nextId());
//            return userDao.insert(dto);
            return userDao.userInsert(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BusinessException(BusinessError.SYSTEM_MANAGER_USER_INSERT_ERROR,e);
        }
    }
}
