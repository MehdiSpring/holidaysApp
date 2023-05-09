package com.mbo.dto;

import com.mbo.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private Integer daysOff;
    private Profession profession;
    private EmployeeDto responsible;
}
