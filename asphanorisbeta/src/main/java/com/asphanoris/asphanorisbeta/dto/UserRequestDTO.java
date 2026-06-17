package com.asphanoris.asphanorisbeta.dto;

import com.asphanoris.asphanorisbeta.enums.RoleType;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String photoUrl;
    private String password;
    private RoleType role;
}