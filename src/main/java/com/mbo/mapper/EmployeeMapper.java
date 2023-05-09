package com.mbo.mapper;

import com.mbo.dto.EmployeeDto;
import com.mbo.model.Employee;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
//@DecoratedWith(EmployeeMapperDecorator.class)
public interface EmployeeMapper {
    EmployeeDto employeeToEmployeeDto(Employee employee);
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
