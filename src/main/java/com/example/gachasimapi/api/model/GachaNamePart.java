package com.example.gachasimapi.api.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GachaNamePart {

    private String name;
    private NamePart type;

    public GachaNamePart(String name, String type){
        this.name = name;
        try {
            this.type = NamePart.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public GachaNamePart(GachaNamePart part){
        this.name = part.getName();
        this.type = part.getType();
    }

    public String getName() {
        return name;
    }

    public NamePart getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(NamePart type) {
        this.type = type;
    }
}
