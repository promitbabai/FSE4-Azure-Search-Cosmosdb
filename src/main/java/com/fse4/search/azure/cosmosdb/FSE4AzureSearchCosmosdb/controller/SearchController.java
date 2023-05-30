package com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.controller;

import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.entity.Associate;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.service.AssociateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/skill-tracker/api/v1/admin")
@Slf4j
public class SearchController {


    @Autowired
    private AssociateService service;

    /**
     *
     * @return - Return Value
     */
    @GetMapping("/getAllAssociates")
    public List<Associate> getAllAssociates(){
        System.out.println("######## Controller method - getAllAssociates");
        List<Associate> associateList = service.getAllAssociates();
        log.info("Sending data back to the Browser");
        for (Associate data : associateList){
            System.out.println("--- Name = " + data.getName());
        }
        return associateList;


    }

}
