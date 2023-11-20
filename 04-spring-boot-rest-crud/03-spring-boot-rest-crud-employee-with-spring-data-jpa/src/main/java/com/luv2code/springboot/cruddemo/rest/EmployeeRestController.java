package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    //define field for employee DAO
    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (using constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
         employeeService = theEmployeeService ;
    }

    // expose "/employees" end point and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //get single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null)
            throw new RuntimeException("Employee id not found : "+employeeId);

        return theEmployee;
    }


    //add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        //set id to 0 to force save new item instead of update

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //Delete existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - "+ employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId ; 
    }

}
