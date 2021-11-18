package com.bank.bankapi.repositories;

import com.bank.bankapi.domain.Employee;
import com.bank.bankapi.exceptions.BAuthException;

public interface EmployeeRepository {
    Integer createEmployee(String firstName, String lastName, String password, Employee.Role role, String phone) throws BAuthException;

    Employee findEmployeeByIdandPassword(String phone,String password) throws BAuthException;

    Integer checkEmployeePhone(String phone);

    Employee findEmployeeById(int user_id);

    boolean deleteEmployeeByPhone(String phone) throws BAuthException;
    boolean updateEmployeeIsActive(Employee employee);
}
