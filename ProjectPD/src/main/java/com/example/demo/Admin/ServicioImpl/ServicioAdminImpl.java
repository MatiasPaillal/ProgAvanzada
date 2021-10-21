/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Admin.ServicioImpl;

import com.example.demo.Admin.modelo.Administrador;
import com.example.demo.Admin.servicios.ServicioAdmin;
import com.example.demo.Admin.Commons.servicioGenImpl;
import com.example.demo.Admin.interfaz.InterfazAdmin;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author matias
 */
@Service
public class ServicioAdminImpl extends servicioGenImpl<Administrador, Integer> implements ServicioAdmin {

    @Autowired
    private InterfazAdmin interfazAdmin;

    @Autowired
    public CrudRepository<Administrador, Integer> obtener() {

        return interfazAdmin;
    }
}
