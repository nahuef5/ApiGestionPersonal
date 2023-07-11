package com.management.staff.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;
@NoArgsConstructor
@Getter
@MappedSuperclass
public class Person{
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Transient
    @JsonIgnore
    private Address address;
    @NotNull
    private String addressCoordinates;
    @NotNull
    private int dni;
    @NotNull
    private LocalDate born;
    @Transient
    @JsonIgnore
    private String email;
    public Person(String name, String surname, Address address, int dni, LocalDate born, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.email = email;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setAddressCoordinates(String addressCoordinates) {
        this.addressCoordinates = addressCoordinates;
    }
}