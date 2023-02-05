/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Productos;
import Modelo.RepositorioProductos;
import Vista.Vista;
import Vista.VistaActualizar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author a-cmo
 */
public class Controlador implements ActionListener{
    
    RepositorioProductos Repositorio;
    Vista Vista;
    VistaActualizar VisAct;
    
    public Controlador(RepositorioProductos Repositorio, Vista Vista, VistaActualizar VisAct){
        this.Repositorio = Repositorio;
        this.Vista = Vista;
        this.VisAct = VisAct;
        agregarEventos();
        listar();
    }
    
    private void agregarEventos(){
        Vista.getBtnAgrPro().addActionListener(this);
        Vista.getBtnBorPro().addActionListener(this);
        Vista.getBtnActPro().addActionListener(this);
        Vista.getBtnInf().addActionListener(this);
        VisAct.getBtnActPro().addActionListener(this);
    }
    
    public List<Productos> listar(){
        List<Productos> ListProductos = (List<Productos>)Repositorio.findAll();
        JTable tabla =  Vista.getTbProductos();
        
        int row = 0;
        for (Productos Pro:ListProductos) {
            tabla.setValueAt(Pro.getCodigo(), row, 0);
            tabla.setValueAt(Pro.getNombre(), row, 1);
            tabla.setValueAt(Pro.getPrecio(), row, 2);
            tabla.setValueAt(Pro.getInventario(), row, 3);
            row++;
        }
        
        for (int i = row; i < tabla.getRowCount(); i++) {
            tabla.setValueAt("", row, 0);  
            tabla.setValueAt("", row, 1); 
            tabla.setValueAt("", row, 2); 
            tabla.setValueAt("", row, 3); 
        }
        return ListProductos;
    }

    public void agregar(){
        try {
            String Nombre = Vista.getTxtNomPro().getText();
            double Precio = Double.parseDouble(Vista.getTxtPrePro().getText());
            int Inventario = Integer.parseInt(Vista.getTxtInvPro().getText());
            System.out.println(Nombre+" y "+Precio+" y "+Inventario);
            if (!Nombre.equals("") && Precio > 0.0 && Inventario > 0) {
                Productos Producto = Productos.CrearProducto(Nombre, Precio, Inventario);
                Repositorio.save(Producto);
                JOptionPane.showMessageDialog(null, "El Producto Fue Agregado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                Vista.setTxtNomPro("");
                Vista.setTxtPrePro("");
                Vista.setTxtInvPro("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todos Los Campos Son Obligatorios o Los Valores Son Invalidos", "Advertencia", JOptionPane.WARNING_MESSAGE); 
        }       
    }
    
    public void eliminar(){
        try {
            JTable tabla =  Vista.getTbProductos();
            Long codigo = (Long)
            tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
            if (codigo > -1) {
                Repositorio.deleteById(codigo);
                JOptionPane.showMessageDialog(null, "El Producto Fue Borrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar Algun Producto Para Borrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void cargarDatos(){
        try {
            JTable tabla = Vista.getTbProductos();
            Long codigo = (Long)
            tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);    
            if (codigo > -1) {
                String Nombre = (String)tabla.getModel().getValueAt(tabla.getSelectedRow(), 1);
                String Precio = (tabla.getModel().getValueAt(tabla.getSelectedRow(), 2)).toString();
                String Inventario = (tabla.getModel().getValueAt(tabla.getSelectedRow(), 3)).toString();
                VisAct.setVisible(true);
                VisAct.setTxtNomProAct(Nombre);
                VisAct.setTxtPreProAct(Precio);
                VisAct.setTxtInvProAct(Inventario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar Algun Producto Para Actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } 
    }
    
    public void actualizar(){
        JTable tabla = Vista.getTbProductos();
        Long codigo = (Long)
        tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
        Productos Pro =  Repositorio.findById(codigo).get();
        String Nombre = VisAct.getTxtNomProAct().getText();
        double Precio = Double.parseDouble(VisAct.getTxtPreProAct().getText());
        int Inventario = Integer.parseInt(VisAct.getTxtInvProAct().getText());
        Pro.setNombre(Nombre);
        Pro.setPrecio(Precio);
        Pro.setInventario(Inventario);
        Repositorio.save(Pro);
        VisAct.setVisible(false);
    }
    
    public void informe(){
        List<Productos> ListProductos = (List<Productos>)Repositorio.findAll();
        double PreMay = 0;
        String NomMay = "";
        double PreMen = 0;
        String NomMen = "";
        double Pro = 0;
        double ValInv = 0;
        for (Productos producto : ListProductos) {
            ValInv += producto.getPrecio() * producto.getInventario();
            Pro += producto.getPrecio();
            if (producto.getPrecio() > PreMay) {
                PreMay = producto.getPrecio();
                NomMay = producto.getNombre();
            }
        }
        PreMen = PreMay;
        for (Productos producto : ListProductos) {
            if (producto.getPrecio() < PreMen) {
                PreMen = producto.getPrecio();
                NomMen = producto.getNombre();
            }            
        }
        Pro /= ListProductos.size();
        JOptionPane.showMessageDialog(null, "Producto Precio Mayor: " + NomMay
                + "\nProducto Precio Menor: " + NomMen
                + "\nPromedio Precio: " + String.format("%.1f", Pro)
                + "\nValor Inventario: " + String.format("%.1f", ValInv), "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Vista.getBtnAgrPro()) {
            agregar();
            listar();
        }
        if (e.getSource() == Vista.getBtnBorPro()) {
            eliminar();
            listar();
        }
        
        if (e.getSource() == Vista.getBtnActPro()) {
            cargarDatos();
            listar();
        }
        
        if (e.getSource() == Vista.getBtnInf()) {
            informe();
        }
        
        if (e.getSource() == VisAct.getBtnActPro()) {
            actualizar();
            listar();
        }
        
    }
    
}
