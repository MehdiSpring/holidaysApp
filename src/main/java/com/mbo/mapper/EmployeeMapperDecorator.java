package com.mbo.mapper;

import com.mbo.dto.EmployeeDto;
import com.mbo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class EmployeeMapperDecorator implements EmployeeMapper{

    /*@Autowired
    @Qualifier("delegate")
    private EmployeeMapper delegate;


    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = delegate.employeeToEmployeeDto(employee);
        employeeDto.setResponsibleName(employee.getResponsible().getLastName());
        return employeeDto;
    }*/


}
