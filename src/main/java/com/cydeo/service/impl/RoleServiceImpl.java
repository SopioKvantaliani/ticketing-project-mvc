package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;

import java.util.List;

public class RoleServiceImpl extends AbstractMapService <RoleDTO, Long> implements RoleService {


    @Override
    public RoleDTO save(RoleDTO role) {
        return super.save (role.getId(), role);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll(); //when we are calling method from parent class, we use Super KeyWord.
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }
}
