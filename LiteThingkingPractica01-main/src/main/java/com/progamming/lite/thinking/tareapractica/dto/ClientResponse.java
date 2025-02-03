package com.progamming.lite.thinking.tareapractica.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {

    private Long id;
    private String documentType;
    private Integer numberDocument;
    private String firstName;
    private String secondName;
    private String numberPhone;
    private String address;
    private String city;
}
