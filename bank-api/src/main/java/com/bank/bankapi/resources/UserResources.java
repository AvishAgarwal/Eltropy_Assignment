package com.bank.bankapi.resources;

import com.bank.bankapi.domain.Employee;
import com.bank.bankapi.domain.User;
import com.bank.bankapi.repositories.EmployeeRepository;
import com.bank.bankapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserResources {

    @Autowired
    UserServices userServices;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> data, HttpServletRequest request){
        int userID= (Integer) request.getAttribute("userId");
        Employee employeeAuth =  employeeRepository.findEmployeeById(userID);
        if(employeeAuth==null )
        {
            Map<String, String> map= new HashMap<>();
            map.put("message","You are not authorized to do this function");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        String firstName = (String)data.get("firstName");
        String lastName= (String)data.get("lastName");
        String phone= (String)data.get("phone");
        String password=(String)data.get("password");

        User user= userServices.registerUser(firstName,lastName,password,userID ,phone);

        Map<String,String> map= new HashMap<>();
        map.put("message","User has been registered");
        return new ResponseEntity<>(map,HttpStatus.OK);

    }

    @PutMapping("/kyc")
    public ResponseEntity<Map<String,String>> updateKyc(@RequestBody Map<String,Object> data, HttpServletRequest request){
        int userID= (Integer) request.getAttribute("userId");
        Employee employeeAuth =  employeeRepository.findEmployeeById(userID);
        if(employeeAuth==null )
        {
            Map<String, String> map= new HashMap<>();
            map.put("message","You are not authorized to do this function");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        String phone= (String)data.get("phone");
        String adhaar=(String)data.get("adhaar");
        String status= (String) data.get("status");

        boolean flag= userServices.updateKyc(phone,adhaar,User.Status.valueOf(status));

        Map<String,String> map= new HashMap<>();
        if(flag)
        map.put("message","KYC updated");
        else
            map.put("message","Unable to update Kyc");
        return new ResponseEntity<>(map,HttpStatus.OK);

    }
}
