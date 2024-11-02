package com.gestionmvp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
//    private List<Empleado> empleados;

    @OneToOne
    //@JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "encargado_id")
    private Empleado encargado;

    private Integer numeroEmpleados;

    @Column(unique = true, nullable = false)
    private String correo;
}
