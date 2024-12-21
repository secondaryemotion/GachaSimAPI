package com.example.gachasimapi.service;


import NameGenerator.DatabaseManager.DatabaseRequester;
import NameGenerator.*;
import com.example.gachasimapi.api.model.GachaName;

import java.sql.SQLException;

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
}
