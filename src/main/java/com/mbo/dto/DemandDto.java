package com.mbo.dto;

import com.mbo.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Statut statut;
    private EmployeeDto employee;
    private EmployeeDto responsible;
}
