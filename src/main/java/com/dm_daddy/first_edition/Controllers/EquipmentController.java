package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.Equipment;
import com.dm_daddy.first_edition.Repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RepositoryRestController
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentRepository repo;

    //----- Load All Equipments ------
    //--------------------------------
    @GetMapping("/equipments")
    public List<Equipment> getAllEquipments(){
        return (List<Equipment>) repo.findAll();
    }

}
