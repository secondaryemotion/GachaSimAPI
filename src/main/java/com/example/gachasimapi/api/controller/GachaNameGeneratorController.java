package com.example.gachasimapi.api.controller;

import com.example.gachasimapi.api.model.GachaName;
import com.example.gachasimapi.service.GachaNameGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class GachaNameGeneratorController {

    private GachaNameGeneratorService gachaNameGeneratorService;

    public GachaNameGeneratorController(GachaNameGeneratorService gachaNameGeneratorService){
        this.gachaNameGeneratorService = gachaNameGeneratorService;
    }

    @GetMapping("/random")
    public GachaName getGachaName() throws SQLException {
        return gachaNameGeneratorService.getGachaName();
    }

}
