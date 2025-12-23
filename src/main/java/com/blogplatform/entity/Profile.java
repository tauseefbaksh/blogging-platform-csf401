package com.blogplatform.entity;

import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String website;
    private String location;

    // EMBEDDED - Contact information
    @Embedded
    private ContactInfo contactInfo;

    @OneToOne
    @JoinColumn(name = "author_id", unique = true)
    @JsonIgnore
    private Author author;
}