package com.gestionmvp.persistence.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignacion_id")
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
