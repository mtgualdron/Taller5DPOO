
package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
    private ProductoMenu productoTest;
    @BeforeEach
    void setUp(){
         productoTest = new ProductoMenu("hamburguesa", 15000);
    }

        @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre(){
        assertEquals("hamburguesa", productoTest.getNombre(), "El nombre del producto no es el esperado");
    }

    @Test
    void testGetPrecio(){
        assertEquals(15000, productoTest.getPrecio(), "El precio del producto no es el esperado");
    }

    @Test
    void testGenerarTextoFactura(){
        assertEquals("hamburguesa\n            15000\n", productoTest.generarTextoFactura(), "El texto de la factura no se genera correctamente");
    }

}