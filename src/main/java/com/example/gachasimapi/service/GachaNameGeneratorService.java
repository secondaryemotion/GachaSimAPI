package com.example.gachasimapi.service;


import NameGenerator.DatabaseManager.DatabaseRequester;
import NameGenerator.*;
import com.example.gachasimapi.api.model.GachaName;
import com.example.gachasimapi.api.model.GachaNamePart;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class GachaNameGeneratorService {

    DatabaseRequester requester;
    NamePartRepository colorRepository;
    NamePartRepository qualityRepository;
    NamePartRepository nounRepository;
    RandomNameGenerator generator;

    public GachaNameGeneratorService(){
        this.requester = new DatabaseRequester();
        this.colorRepository = new ColorRepository(requester);
        this.qualityRepository = new QualityRepository(requester);
        this.nounRepository = new NounRepository(requester);
        this.generator = new RandomNameGenerator(colorRepository, qualityRepository, nounRepository);
    }

    public GachaName getGachaName() throws SQLException {
        GachaName name = new GachaName(generator.generateName());
        return name;
    }

    public ArrayList<GachaNamePart> getAllParts(String part) {
        String[] parts = getRepositoryByType(part).getAllValues();
        ArrayList<GachaNamePart> list = new ArrayList<>();
        for (String name: parts){
            list.add(new GachaNamePart(name,part));
        }
        return list;
    }

    public NamePartRepository getRepositoryByType(String part) {
        return switch (part) {
            case "color" -> colorRepository;
            case "quality" -> qualityRepository;
            case "noun" -> nounRepository;
            default ->  throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        };
    }

    public void createValue(GachaNamePart namePart) {
        getRepositoryByType(namePart.getType()).addValue(namePart.getName());
    }

    public void updateValue(GachaNamePart namePart, String newValue){
        getRepositoryByType(namePart.getType()).updateValue(namePart.getName(),newValue);
    }

    public void deletevalue(GachaNamePart namePart){
        getRepositoryByType(namePart.getType()).deleteValue(namePart.getName());
    }

}
