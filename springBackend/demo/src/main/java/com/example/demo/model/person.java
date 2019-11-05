package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.UUID;

public class person {

    private final UUID id;

    @NotBlank
    private final String name;

    //@NotBlank
    private final Date birthday;

    public person(@JsonProperty("id") UUID id,
                  @JsonProperty("name")String name,
                  @JsonProperty("birthday") Date birthday){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public UUID getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
