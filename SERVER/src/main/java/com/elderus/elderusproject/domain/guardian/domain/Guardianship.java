package com.elderus.elderusproject.domain.guardian.domain;


import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="Guardianship")
@Data
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Guardianship {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guardianshipId;

    @ManyToOne
    @NotNull
    private Guardian guardian;

    @ManyToOne
    @NotNull
    private Ward ward;




}
