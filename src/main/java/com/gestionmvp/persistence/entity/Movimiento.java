package com.gestionmvp.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestionmvp.util.EmpleadoDeserializer;
import com.gestionmvp.util.EmpleadoSerializer;
import com.gestionmvp.util.ProductoDeserializer;
import com.gestionmvp.util.ProveedorDeserializer;
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
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonDeserialize(using = ProductoDeserializer.class)
    private Producto productoMovimiento;

    @Column(name = "tipo_movimiento")
    @Enumerated(EnumType.STRING)
    private TipoMovimientoEnum tipoMovimientoEnum;

    private Integer cantidad;

    private String descripcion;

    private LocalDateTime fechaMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    @JsonDeserialize(using = EmpleadoDeserializer.class)
    @JsonSerialize(using = EmpleadoSerializer.class)
    private Empleado responsable;

}
