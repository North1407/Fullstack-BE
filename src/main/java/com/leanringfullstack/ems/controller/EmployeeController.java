package com.leanringfullstack.ems.controller;

import com.leanringfullstack.ems.dto.EmployeeDto;
import com.leanringfullstack.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDtoSaved = employeeService.create(employeeDto);
        return ResponseEntity.ok(employeeDtoSaved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeService(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.getEmployee(id);
        return ResponseEntity.ok(employeeDto);
    }
    @GetMapping("/{id}/check_email")
    public ResponseEntity<Boolean> getEmployeeServiceByEmail(@PathVariable Long id, @RequestParam String email) {
        Boolean checkEmail = employeeService.checkEmail(id, email);
        return ResponseEntity.ok(checkEmail);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDtoSaved = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDtoSaved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
