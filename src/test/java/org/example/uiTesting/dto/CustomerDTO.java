package org.example.uiTesting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String postCode;
}
