package com.leanringfullstack.ems.service;

import com.leanringfullstack.ems.constant.Constants;
import com.leanringfullstack.ems.dto.EmployeeDto;
import com.leanringfullstack.ems.entity.Employee;
import com.leanringfullstack.ems.exception.ResourceNotFoundException;
import com.leanringfullstack.ems.mapper.EmployeeMapper;
import com.leanringfullstack.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.toEntity(employeeDto);
        Employee employeeSaved =  employeeRepository.save(employee);
        return EmployeeMapper.toDto(employeeSaved);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.NO_EMPLOYEE));
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            EmployeeDto dto = EmployeeMapper.toDto(employee);
            list.add(dto);
        }
        return list;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.NO_EMPLOYEE));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee employeeSaved = employeeRepository.save(employee);
        return EmployeeMapper.toDto(employeeSaved);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.NO_EMPLOYEE));
        employeeRepository.delete(employee);
    }

    @Override
    public Boolean checkEmail(Long id, String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElse(null);
        if (employee == null) {
            return true;
        }
        boolean isCreatingNew = (id == null);
        return isCreatingNew || Objects.equals(employee.getId(), id);
    }
}
