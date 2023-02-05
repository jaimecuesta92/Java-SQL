package Modelo;

import Controlador.Controlador;
import Vista.Vista;
import Vista.VistaActualizar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class inventarioApp {
    
    @Autowired
    RepositorioProductos Repositorio;

	public static void main(String[] args) {
            //SpringApplication.run(inventarioApp.class, args);
            
            SpringApplicationBuilder builder = new SpringApplicationBuilder(inventarioApp.class);
            builder.headless(false);
            ConfigurableApplicationContext context = builder.run(args);
	}
        
    @Bean
    ApplicationRunner applicationRunner(){
        return args ->{
            
            final Log logger = LogFactory.getLog(getClass());
                      
            Productos Manzanas = Productos.CrearProducto("Manzanas", 5000, 25);
            Productos Limones = Productos.CrearProducto("Limones", 2300, 15);
            Productos Peras = Productos.CrearProducto("Peras", 2700, 25);
            Productos Arandanos = Productos.CrearProducto("Arandanos", 9300, 5);
            Productos Tomates = Productos.CrearProducto("Tomates", 2100, 42);
            Productos Fresas = Productos.CrearProducto("Fresas", 4100, 3);
            Productos Helado = Productos.CrearProducto("Helado", 4500, 41);
            Productos Galletas = Productos.CrearProducto("Galletas", 500, 8);
            Productos Chocolates = Productos.CrearProducto("Chocolates", 3500, 80);
            Productos Jamon = Productos.CrearProducto("Jamon", 15000, 10);
            
            Repositorio.save(Manzanas);
            Repositorio.save(Limones);
            Repositorio.save(Peras);
            Repositorio.save(Arandanos);
            Repositorio.save(Tomates);
            Repositorio.save(Fresas);
            Repositorio.save(Helado);
            Repositorio.save(Galletas);
            Repositorio.save(Chocolates);
            Repositorio.save(Jamon);
            
            Controlador controlador = new Controlador(Repositorio, new Vista(), new VistaActualizar());
        };
    }

}
