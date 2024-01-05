package com.leanringfullstack.ems.dto;

import com.leanringfullstack.ems.entity.IdBasedEntity;
import lombok.Data;

@Data
public class EmployeeDto extends IdBasedEntity {
    private String firstName;
    private String lastName;
    private String email;
}
