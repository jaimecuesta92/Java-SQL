/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author mnemosoc
 */
    @Table("productos")
    public class Productos {
        @Id
        @Column("codigo")
        private Long codigo;
        @Column("nombre") 
        private String nombre;
        @Column("precio")
        private double precio;
        @Column("inventario")
        private int inventario;

        private Productos(Long codigo, String nombre, double precio, int inventario) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.inventario = inventario;
        }
        
        public static Productos CrearProducto(String nombre, double precio, int inventario){
            return new Productos(null, nombre, precio, inventario);
        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public int getInventario() {
            return inventario;
        }

        public void setInventario(int inventario) {
            this.inventario = inventario;
        }
    }
