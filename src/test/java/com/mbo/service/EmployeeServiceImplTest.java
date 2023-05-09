package com.mbo.service;

import com.mbo.dto.EmployeeDto;
import com.mbo.enums.Profession;
import com.mbo.mapper.EmployeeMapper;
import com.mbo.model.Employee;
import com.mbo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.CapturesArguments;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    EmployeeServiceImpl employeeService;

    EmployeeDto employeeDto;
    EmployeeDto responsibleDto;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
        employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);

        responsibleDto = EmployeeDto.builder()
                .firstName("hakim")
                .lastName("Akchour")
                .profession(Profession.MANAGER)
                .build();

        employeeDto = EmployeeDto.builder()
                .firstName("Mohamed")
                .lastName("Hassan")
                .profession(Profession.ACCOUNTANT)
                .responsible(responsibleDto)
                .build();
    }

    @Test
    void shouldMapEmployeeDtoToEmployee()
    {
        //Given
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        //When, Then
        employeeService.create(employeeDto);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        Employee employee = employeeArgumentCaptor.getValue();

        assertEquals(employeeDto.getFirstName(), employee.getFirstName());
        assertEquals(employeeDto.getLastName(), employee.getLastName());
        assertEquals(employeeDto.getProfession(), employee.getProfession());
        assertEquals(employeeDto.getResponsible().getFirstName(), employee.getResponsible().getFirstName());

    }

    @Test
    void shouldSaveEmployeeAndReturnEmployeeDto()
    {
        //Given
        Employee employee = Employee.builder()
                .firstName("Mohamed")
                .lastName("Hassan")
                .profession(Profession.ACCOUNTANT)
                .build();

        //When
        when(employeeRepository.save(any())).thenReturn(employee);

        //Then
        EmployeeDto employeeDto1 = employeeService.create(employeeDto);
        assertEquals(employee.getFirstName(), employeeDto1.getFirstName());
        assertEquals(employee.getLastName(), employeeDto1.getLastName());
        assertEquals(employee.getProfession(), employeeDto1.getProfession());

    }

    @Test
    void shouldThrowAnExceptionIfIdIsNotFound()
    {
        //Given
        UUID id = UUID.randomUUID();
        Optional<Employee> noEmployee = Optional.empty();

        //When
        when(employeeRepository.findById(any())).thenReturn(noEmployee);

        //Then
        assertThrows(RuntimeException.class, ()-> {
           employeeService.update(id, EmployeeDto.builder().build());
        });

    }

    @Test
    void shouldUpdateTheEmployeeWhenIsFound()
    {
        //Given
        UUID id = UUID.randomUUID();
        Employee employeeToUpdate = Employee.builder().id(id).build();

        EmployeeDto employeeDto1 = EmployeeDto.builder().firstName("Mehdi").build();
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        //When
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employeeToUpdate));

        //Then
        employeeService.update(id,employeeDto1);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        Employee employeeUpdated = employeeArgumentCaptor.getValue();
        assertEquals(id, employeeUpdated.getId());
        assertEquals(employeeDto1.getFirstName(), employeeUpdated.getFirstName());
    }

    @Test
    void shouldDeleteTheEmployee()
    {
        //Given
        UUID id = UUID.randomUUID();

        //When, Then
        employeeService.delete(id);
        verify(employeeRepository, times(1)).deleteById(any());
    }
}