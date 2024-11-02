package com.gestionmvp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gestionmvp.util.AreaDeserializer;
import com.gestionmvp.util.CargoDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id")
    @JsonDeserialize(using = CargoDeserializer.class)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonManagedReference
    @JoinColumn(name = "area_id")
    @JsonDeserialize(using = AreaDeserializer.class)
    private Area area;

    private LocalDate fechaIngreso;

    @Enumerated(EnumType.STRING)
    private EstadoLaboralEnum estadoLaboralEnum;

    @Column(unique = true, nullable = false)
    private String correo;

    private String telefono;

}
