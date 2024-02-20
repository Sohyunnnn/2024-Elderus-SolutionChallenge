package com.elderus.elderusproject.domain.ward.domain;


import com.elderus.elderusproject.domain.ward.domain.Ward;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Disease")
@NoArgsConstructor
@Getter
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Ward ward;

    @NotNull
    private String disease;

}
