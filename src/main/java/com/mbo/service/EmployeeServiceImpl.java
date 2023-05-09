package com.mbo.service;

import com.mbo.dto.EmployeeDto;
import com.mbo.mapper.EmployeeMapper;
import com.mbo.model.Employee;
import com.mbo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {

        return employeeMapper.employeeToEmployeeDto(
                employeeRepository.save(
                        employeeMapper.employeeDtoToEmployee(employeeDto))
        );
    }

    @Override
    public void update(UUID id, EmployeeDto employeeDto) {
        Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("There is no Employee found with the id provided !")
        );

        Employee employeeUpdated = employeeMapper.employeeDtoToEmployee(employeeDto);
        employeeUpdated.setId(employeeToUpdate.getId());

        employeeRepository.save(employeeUpdated);


    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }
}
