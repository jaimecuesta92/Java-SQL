
import java.util.HashMap;
import java.util.Scanner;
        
class Reto3 {
    private final Scanner scanner = new Scanner (System.in);
    
    
    public String read(){
       return this.scanner.nextLine();
       
    }
    
  public void run(){
     BaseDatosProductos baseProducto = new BaseDatosProductos();
     baseProducto.llenarMapa();
     
     
     String operacion =read();
     String[] datos = read().split(" ");
     
     Producto productoEntrada = new Producto( 
               Integer.parseInt(datos[0]),
               datos [1],
               Double.parseDouble(datos[2]),
               Integer.parseInt(datos[3])
           );
               
               
               if( operacion.equals ("AGREGAR")) {
                 if (baseProducto.verificarExistencia(productoEntrada)){
                     System.out.println("ERROR");
               } else {
                 baseProducto.agregar(productoEntrada);
                 baseProducto.generarInforme();
                 
               }
               }else if ( operacion.equals ("ACTUALIZAR")){
                 if ( baseProducto.verificarExistencia(productoEntrada)) {
                    baseProducto.actualizar (productoEntrada);
                    baseProducto.generarInforme();
                
                }else {
                    System.out.println("ERROR");
                }
                    
               }else if (operacion.equals ("BORRAR")){
                  if ( baseProducto.verificarExistencia(productoEntrada)){
                       baseProducto.borrar(productoEntrada);
                       baseProducto.generarInforme();
                       
                  }else {
                         System.out.println("ERROR");    
                  
                  }
               
               
               }
                
                }
               
               }                    
               

 class BaseDatosProductos {
     
    private HashMap<Integer, Producto> listaProductos = new HashMap<Integer,Producto>();
    
    public void llenarMapa(){
    listaProductos.put(1,new Producto (1,"Manzanas" , 5000.0, 25 ));
    listaProductos.put(2,new Producto (2,"Limones" , 2300.0, 15 ));
    listaProductos.put(3,new Producto (3,"Peras" , 2700.0, 33 ));
    listaProductos.put(4,new Producto (4,"Arandanos" , 9300.0, 5 ));
    listaProductos.put(5,new Producto (5,"Tomates" , 2100.0, 42 ));
    listaProductos.put(6,new Producto (6,"Fresas" , 4100.0, 3 ));
    listaProductos.put(7,new Producto (7,"Helado" , 4500.0, 41 ));
    listaProductos.put(8,new Producto (8,"Galletas" , 500.0, 8 ));
    listaProductos.put(9,new Producto (9,"Chocolates" , 3500.0, 80 ));
    listaProductos.put(10,new Producto (10,"Jamon" , 15000.0, 10 ));
    }
    
    public void agregar (Producto producto){
         listaProductos.put(producto.getCodigo(), producto);
    }
    
    public void actualizar (Producto producto){
         listaProductos.replace(producto.getCodigo(), producto);
    }
         
    public void borrar (Producto producto){
         listaProductos.remove(producto.getCodigo());
    }
    
    public boolean  verificarExistencia (Producto producto){
         return listaProductos.containsKey(producto.getCodigo());
    }
    
    public void generarInforme (){
       Double precioMayor = 0.0;
       Double precioMenor = 99999999999999.9;
       Double sumatoriaPrecios = 0.0;
       String nombrePrecioMayor = "";
       String nombrePrecioMenor = "";
       
       
       for ( Producto pro : listaProductos.values() ){
           if (pro.getPrecio() > precioMayor){
           precioMayor = pro.getPrecio();
           nombrePrecioMayor = pro.getNombre();
          
           }     
       
           if (pro.getPrecio() < precioMenor){
            precioMenor    = pro.getPrecio();
            nombrePrecioMenor  = pro.getNombre();
           }
           
         sumatoriaPrecios += pro.getPrecio();    
       }
       
        Double promedio = sumatoriaPrecios /listaProductos.size();
        
        System.out.println(nombrePrecioMayor + " " + nombrePrecioMenor + " " + String.format ("%.1f", promedio ));
    }
       
 }   
    
        class Producto {
        
            private Integer codigo;
            private String  nombre;
            private Double  precio;
            private Integer inventario;
            
            
        public Producto (Integer codigo, String nombre, Double precio, Integer inventario){
           this.codigo = codigo;
           this.nombre = nombre;  
           this.precio = precio;
           this.inventario = inventario;
           
           
        }
        
        public Integer getCodigo() {
             return codigo;
        }
        
        public void setCodigo(Integer codigo) { 
              this.codigo = codigo;
        
        }
        
         public String  getNombre() {
             return nombre;
         }
         
       public void  setNombre(String nombre) {
           this.nombre=nombre;
           
       }
        
       
       public Double getPrecio(){
         return precio;
       }
       
       
       public void setPrecio(Double precio){
        this.precio =precio; 
       }

       
       
       public Integer getInventario (){
          return inventario;
       }
       
       public void setInventario(Integer inventario) {
        this.inventario = inventario;       
       
       }   
        
    }
         
         
    
    
    
    
    
    
   
               
              
               
  
  
  
  
  
  
  
          
          
          
        

