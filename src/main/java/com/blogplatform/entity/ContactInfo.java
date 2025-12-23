package com.blogplatform.entity;

import lombok.*;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {

    private String phone;

    @Email(message = "Invalid email format")
    private String alternateEmail;

    private String linkedin;
    private String twitter;
}