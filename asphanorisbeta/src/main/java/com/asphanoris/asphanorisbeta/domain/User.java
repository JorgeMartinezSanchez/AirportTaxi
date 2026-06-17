package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.enums.RoleType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class User {
    public Long id;
    public String firstName;
    public String secondName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String photoUrl;
    public String passwordHash;
    public RoleType role;
    
    public User modifyUser(User newData) {
        if (newData.getFirstName() != null) this.firstName = newData.getFirstName();
        if (newData.getSecondName() != null) this.secondName = newData.getSecondName();
        if (newData.getLastName() != null) this.lastName = newData.getLastName();
        if (newData.getEmail() != null) this.email = newData.getEmail();
        if (newData.getPhoneNumber() != null) this.phoneNumber = newData.getPhoneNumber();
        if (newData.getPhotoUrl() != null) this.photoUrl = newData.getPhotoUrl();
        return this;
    }

    // Método de copia protegido
    protected void copyFrom(User source) {
        this.id = source.id;
        this.firstName = source.firstName;
        this.secondName = source.secondName;
        this.lastName = source.lastName;
        this.email = source.email;
        this.phoneNumber = source.phoneNumber;
        this.photoUrl = source.photoUrl;
        this.passwordHash = source.passwordHash;
        this.role = source.role;
    }
}