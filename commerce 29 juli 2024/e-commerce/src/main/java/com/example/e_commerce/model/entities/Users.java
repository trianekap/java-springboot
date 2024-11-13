package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 3, message = "first name should have at least 3 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 3, message = "last name should have at least 3 characters")
    private String lastName;

    @NotBlank
    @Size(min = 10, max = 13, message = "mobile number minimal 10 digits and not then 13 digits")
    @Pattern(regexp="^(\\+62|62|0)8[1-9]\\d{8,10}$", message = "mobile number is not valid!")
    @Column(unique = true)
    private String mobileNumber;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, message = "password should have at 8 characters")
    private String password;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime created_at;
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime modified_at;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Carts> carts = new HashSet<>();

    @OneToMany(mappedBy = "users2", cascade = CascadeType.ALL)
    private Set<UsersActivityLogEntity> usersActivityLogEntitySet = new HashSet<>();

}


