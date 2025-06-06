package com.japi.userapi.domain.model;

import lombok.Data;

@Data
public class Address {
    private String zipCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
}
