package com.gestionmvp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestionmvp.persistence.entity.EstadoProductoEnum;
import com.gestionmvp.persistence.entity.Producto;
import com.gestionmvp.persistence.entity.Proveedor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ProductoDTO {

    private Long id;
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String proveedor;
    private EstadoProductoEnum estadoProductoEnum;

    public ProductoDTO(Producto producto){
        this.id = producto.getId();
        this.nombreProducto = producto.getNombreProducto();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.stock = producto.getStock();
        this.proveedor = producto.getProveedor().getNombreProveedor();
        this.estadoProductoEnum = producto.getEstadoProductoEnum();

    }

//    @JsonProperty("precio")
//    public String getPrecio(){
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
//        return numberFormat.format(precio);
//    }
}
