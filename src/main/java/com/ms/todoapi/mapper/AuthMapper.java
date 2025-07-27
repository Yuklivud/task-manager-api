package com.ms.todoapi.mapper;

import com.ms.todoapi.dto.SignUpRequest;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.repository.RoleRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AuthMapper {
    @Autowired
    private RoleRepository roleRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    public abstract User toUser(SignUpRequest signUpRequest);

    @AfterMapping
    protected void setDefaultRole(@MappingTarget User user){
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role USER not found"));
        user.setRoles(Set.of(role));
    }
}