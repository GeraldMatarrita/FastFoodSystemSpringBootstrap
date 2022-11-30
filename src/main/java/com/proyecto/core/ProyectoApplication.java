package com.proyecto.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ProyectoApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(ProyectoApplication.class, args);
    }
}
