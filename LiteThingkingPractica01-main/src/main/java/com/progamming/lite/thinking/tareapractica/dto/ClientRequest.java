package com.progamming.lite.thinking.tareapractica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {
    @Pattern(regexp = "[C|P]", message = "documentType solo permite C = Cedula o P = passaporte")
    private String documentType;
    @NotNull(message = "numberDocument no puede ser null")
    private Integer numberDocument;
    @NotNull(message = "firstName no puede ser null")
    @NotBlank(message = "firstName no puede estar en blanco")
    private String firstName;
    @NotNull(message = "secondName no puede ser null")
    @NotBlank(message = "secondName no puede estar en blanco")
    private String secondName;
    private String firstlastName;
    private String secondlastName;
    private String numberPhone;
    private String address;
    private String city;
}
