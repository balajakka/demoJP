package com.example.demoJP.model.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;
}
