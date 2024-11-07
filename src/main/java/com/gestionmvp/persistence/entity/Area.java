package com.gestionmvp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestionmvp.util.EmpleadoDeserializer;
import com.gestionmvp.util.EmpleadoSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    private String descripcion;

    @OneToOne
    //@JsonBackReference
    //@JsonIgnore
    @JsonDeserialize(using =EmpleadoDeserializer.class)
    @JsonSerialize(using = EmpleadoSerializer.class)
    @JoinColumn(name = "encargado_id")
    private Empleado encargado;

    private Integer numeroEmpleados;

    @Column(unique = true, nullable = false)
    private String correo;
}
