package com.cydeo.boostrap;

import com.cydeo.dto.RoleDTO;
import org.springframework.boot.CommandLineRunner;

public class DataGenerator implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        RoleDTO adminRole = new RoleDTO(1L, "Admin"); //Adding roles in DataBase;
        RoleDTO managerRole = new RoleDTO(2L, "Manager"); //Adding roles in DataBase;
        RoleDTO employeeRole = new RoleDTO(3L, "Employee"); //Adding roles in DataBase;



    }
}

//This interface comes with Spring. Whenever we execute application this method runs first.