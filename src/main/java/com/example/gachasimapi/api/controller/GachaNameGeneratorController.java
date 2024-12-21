package com.example.gachasimapi.api.controller;

import com.example.gachasimapi.api.model.GachaName;
import com.example.gachasimapi.api.model.GachaNamePart;
import com.example.gachasimapi.service.GachaNameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class GachaNameGeneratorController {

    private GachaNameGeneratorService gachaNameGeneratorService;

    @Autowired
    public GachaNameGeneratorController(GachaNameGeneratorService service){
        this.gachaNameGeneratorService = service;
    }

    @GetMapping("/random")
    public GachaName getGachaName() throws SQLException {
        return gachaNameGeneratorService.getGachaName();
    }

    @GetMapping("/all")
    public ArrayList<GachaNamePart> getAllParts(String part) {
        return gachaNameGeneratorService.getAllParts(part);
    }

    @PostMapping("/create")
    public void createValue(@RequestBody GachaNamePart newNamePart){
        gachaNameGeneratorService.createValue(new GachaNamePart(newNamePart.getName(),newNamePart.getType()));
    }

    @PutMapping("/replace")
    public void updateValue(@RequestBody GachaNamePart namePart, @RequestParam(value="value") String newValue){
        gachaNameGeneratorService.updateValue(namePart,newValue);
    }

    @DeleteMapping("/delete")
    public void deletevalue(@RequestBody GachaNamePart namePart){
        gachaNameGeneratorService.deletevalue(namePart);
    }
}
