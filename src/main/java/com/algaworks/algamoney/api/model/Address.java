package com.algaworks.algamoney.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    @Column(name = "public_place")
    private String publicPlace;

    private String number;
    private String complement;
    private String neighborhood;
    private String CEP;
    private String city;
    private String state;

}
