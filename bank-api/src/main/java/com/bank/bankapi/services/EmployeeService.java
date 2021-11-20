package com.bank.bankapi.services;

import com.bank.bankapi.domain.Employee;
import com.bank.bankapi.exceptions.BAuthException;

public interface EmployeeService {
    Employee validateEmployee(String phone, String password,boolean status) throws BAuthException;

    Employee registerEmployee(String firstName, String lastName, String password, Employee.Role role, String phone) throws BAuthException;

    boolean deleteEmployee(String phone) throws BAuthException;

    Employee logout(Employee employee) throws BAuthException;
}
