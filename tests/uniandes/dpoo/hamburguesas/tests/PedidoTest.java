package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;

public class PedidoTest {
    private Pedido pedidoTest; 

    @BeforeEach
    void setUp(){
        pedidoTest = new Pedido("Mateo", "direccion test");
    }

    @Test
    void testGetNombreCliente(){
        assertEquals("Mateo", pedidoTest.getNombreCliente(), "La función no retorna el nombre correcto del cliente");
    }

    @Test 
    void testGetIdPedido(){
        Pedido pedido1 = new Pedido("test1", "direccion1");
        Pedido pedido2 = new Pedido("test2", "direccion2");

        assertEquals(pedido1.getIdPedido()+ 1, pedido2.getIdPedido(), "La función no retorna el id correcto");
    }

    @Test
    void testAgregarProducto(){
        Producto hamburguesa = new ProductoMenu("hamburguesa", 15000);
        pedidoTest.agregarProducto(hamburguesa);
        assertEquals(1, pedidoTest.getCantidadProductos());
    }

    @Test
    void testGetPrecioTotalPedido(){
        Producto hamburguesa = new ProductoMenu("hamburguesa", 15000);
        pedidoTest.agregarProducto(hamburguesa);
        Producto papas = new ProductoMenu("papas", 8000);
        pedidoTest.agregarProducto(papas);
        Producto gaseosa = new ProductoMenu("gaseosa", 6000);
        pedidoTest.agregarProducto(gaseosa);

        assertEquals(34510, pedidoTest.getPrecioTotalPedido(), "El métdodo no calcula el precio total correctamente");
    }

    @Test
    void testGenerarTextoFactura(){
        Producto hamburguesa = new ProductoMenu("hamburguesa", 15000);
        pedidoTest.agregarProducto(hamburguesa);
        assertEquals(
        "Cliente: Mateo\n" +
        "Dirección: direccion test\n" +
        "----------------\n" +
        "hamburguesa\n            15000\n" +
        "----------------\n" +
        "Precio Neto:  15000\n" +
        "IVA:          2850\n" +        
        "Precio Total: 17850\n",
        pedidoTest.generarTextoFactura(),
        "El texto de la factura no se genera correctamente");
}
    @Test
    void testGuardarFactura() throws Exception{
        Producto productoTest = new ProductoMenu("hamburguesa", 15000);
        pedidoTest.agregarProducto(productoTest);
        File archivoTest = File.createTempFile("archivoTest", ".exe");
        archivoTest.deleteOnExit();
        pedidoTest.guardarFactura(archivoTest);
        String contenido = new String(Files.readAllBytes(archivoTest.toPath()));
        assertEquals(pedidoTest.generarTextoFactura(), contenido);
    }
}