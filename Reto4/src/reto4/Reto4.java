/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reto4 {

    public static void main(String[] args) {
        BaseDatosProductos listaProductos = new BaseDatosProductos();
        Scanner sc = new Scanner(System.in);
        String opc = sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        Producto producto;
        producto = new Producto(Integer.parseInt(input[0]),
                input[1], Double.parseDouble(input[2]), Integer.parseInt(input[3]));
        switch (opc) {
            case "AGREGAR":
                if (listaProductos.verificar(producto)) {
                    System.out.println("ERROR");
                } else {
                    listaProductos.agregar(producto);
                    listaProductos.informe2();
                }
                break;
            case "ACTUALIZAR":
                if (listaProductos.verificar(producto)) {
                    listaProductos.actualizar(producto);
                    listaProductos.informe2();
                } else {
                    System.out.println("ERROR");
                }
                break;
            case "BORRAR":
                if (listaProductos.verificar(producto)) {
                    listaProductos.borrar(producto);
                    listaProductos.informe2();
                } else {
                    System.out.println("ERROR");
                }
                break;

        }

    }

}

class Producto {

    private int codigo;
    private String nombre;
    private double precio;
    private int inventario;

//==========================================================================================================================
//                                                   
//==========================================================================================================================
    public Producto(int codigo, String nombre, double precio, int inventario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

class BaseDatosProductos {

    private Map<Integer, Producto> listaProductos = new HashMap<Integer, Producto>();

    public BaseDatosProductos() {
        listaProductos.put(1, new Producto(1, "Manzanas", 5000, 25));
        listaProductos.put(2, new Producto(2, "Limones", 2300, 15));
        listaProductos.put(3, new Producto(3, "Peras", 2700, 33));
        listaProductos.put(4, new Producto(4, "Arandanos", 9300, 5));
        listaProductos.put(5, new Producto(5, "Tomates", 2100, 42));
        listaProductos.put(6, new Producto(6, "Fresas", 4100, 3));
        listaProductos.put(7, new Producto(7, "Helado", 4500, 41));
        listaProductos.put(8, new Producto(8, "Galletas", 500, 8));
        listaProductos.put(9, new Producto(9, "Chocolate", 3500, 80));
        listaProductos.put(10, new Producto(10, "Jamon", 15000, 10));
    }

    public boolean verificar(Producto producto) {
        return listaProductos.containsKey(producto.getCodigo());
    }

    public void agregar(Producto producto) {
        listaProductos.put(producto.getCodigo(), producto);
    }

    public void actualizar(Producto producto) {
        listaProductos.replace(producto.getCodigo(), producto);
    }

    public void borrar(Producto producto) {
        listaProductos.remove(producto.getCodigo());
    }

    public String mayor() {
        String nombre = "";
        double precio = 0;
        for (Producto producto : listaProductos.values()) {
            if (producto.getPrecio() > precio) {
                nombre = producto.getNombre();
                precio = producto.getPrecio();
            }
        }
        return nombre;
    }

    public String menor() {
        String nombre = listaProductos.values().iterator().next().getNombre();
        double precio = listaProductos.values().iterator().next().getPrecio();
        for (Producto producto : listaProductos.values()) {
            if (producto.getPrecio() < precio) {
                nombre = producto.getNombre();
                precio = producto.getPrecio();
            }
        }
        return nombre;
    }

    public double promedio() {
        double total = 0;
        for (Producto producto : listaProductos.values()) {
            total = total + producto.getPrecio();
        }
        return total / listaProductos.size();
    }

    public void informe2() {
        double total = 0;
        System.out.println(mayor() + " " + menor() + " " + String.format("%.1f", promedio()));

    }

}
