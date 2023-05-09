package com.mbo.service;

import com.mbo.dto.EmployeeDto;

import java.util.UUID;

public interface EmployeeService {

    EmployeeDto create(EmployeeDto employeeDto);
    void update(UUID id, EmployeeDto employeeDto);
    void delete(UUID id);
}
