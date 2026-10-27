package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
    private ProductoAjustado productoTest;
    private ProductoMenu productoMenu;

    @BeforeEach
    void setUp(){
        productoMenu = new ProductoMenu("hamburguesa", 15000);
        productoTest = new ProductoAjustado(productoMenu);
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
        assertEquals(productoMenu.generarTextoFactura() + "            15000\n", productoTest.generarTextoFactura(), "El texto de la factura no se genera correctamente");
}
}