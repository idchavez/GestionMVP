package com.gestionmvp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestionmvp.util.EmpleadoDeserializer;
import com.gestionmvp.util.EmpleadoSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignacion_id")
    @JsonDeserialize(using = EmpleadoDeserializer.class)
    @JsonSerialize(using = EmpleadoSerializer.class)
    //@JsonIgnoreProperties({"nombres","apellidos","cargo","area","fechaIngreso","estadoLaboralEnum","correo","telefono"})
    private Empleado empleadoAsignado;

    private String descripcion;

    private LocalDateTime creacion;

    private LocalDateTime vencimiento;

    @Column(name = "prioridad")
    @Enumerated(EnumType.STRING)
    private PrioridadEnum prioridadEnum ;

    @Column(name = "estado_actividad")
    @Enumerated(EnumType.STRING)
    private EstadoActividadEnum estadoActividadEnum;
}
