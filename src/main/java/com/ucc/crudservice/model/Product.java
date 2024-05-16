package com.ucc.crudservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name ="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @NotBlank(message = "el campus de sku esta vacio")
    private String sku;

    @NotBlank(message = "el campus de name esta vacio")
    private  String name;

    @NotBlank(message = "el campus de description esta vacio")
    private  String description;

    @NotNull(message = "el campus de price esta vacio")
    @DecimalMin(value = "0.00",message = "el valor deberia ser mayor o igual a cero")
    private  Double price;

    private  Boolean status;
}
