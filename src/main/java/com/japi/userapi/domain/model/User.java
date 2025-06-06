package com.japi.userapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.japi.userapi.domain.service.AddressService;
import com.japi.userapi.domain.model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    public User(String name, String email, AddressService enderecoService) {
        this.name = name;
        this.email = email;
        
        var zipCode = "83604540"; // Default zip code;
        this.address = enderecoService.getAddressByCep(zipCode);
    }
    
    @Id
    private String id;
    private String name;
    private String email;
    @Field
    private Address address;

    public User(String name, String email, String zipCode, AddressService enderecoService) {
        if (zipCode == null || zipCode.isEmpty()) {
           zipCode = "83604540"; // Default zip code;
        }
        
        this.name = name;
        this.email = email;
        this.address = enderecoService.getAddressByCep(zipCode);
    }
}