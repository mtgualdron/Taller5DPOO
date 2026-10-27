package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Combo;

public class ComboTest {

private Combo comboTest;
    @BeforeEach
    void setUp(){
        ProductoMenu hamburguesa = new ProductoMenu("hamburguesa", 15000);
        ProductoMenu papasGrandes = new ProductoMenu("papas grandes", 8000);
        ProductoMenu gaseosaMediana = new ProductoMenu("gaseosa mediana", 6000);
        ArrayList<ProductoMenu> listaProductos= new ArrayList<>();
        listaProductos.add(hamburguesa);
        listaProductos.add(gaseosaMediana);
        listaProductos.add(papasGrandes);


        comboTest = new Combo("Combo hamburguesa", 0.15, listaProductos);
    }

        @Test
        void testGetNombre(){
            assertEquals("Combo hamburguesa", comboTest.getNombre(), "El nombre del combo no es el esperado");
        }

        @Test
        void testGetPrecio(){
            assertEquals(24650, comboTest.getPrecio(), "El precio del combo no es el esperado");
        }

        @Test
        void testGenerarTextoFactura(){
            assertEquals("Combo Combo hamburguesa\n Descuento: 0.15\n            24650\n", comboTest.generarTextoFactura(), "El texto de la factura no se genera correctamente");
        }
}