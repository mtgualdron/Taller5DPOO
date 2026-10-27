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
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;

public class RestauranteTest {
    private Restaurante restauranteTest; 
    @BeforeEach
    void setUp(){
        restauranteTest = new Restaurante();
    }

    @Test
    void testIniciarNuevoPedido() throws YaHayUnPedidoEnCursoException{
        restauranteTest.iniciarPedido("Mateo", "direccion");
        assertEquals("Mateo", restauranteTest.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    void testIniciarPedidoNulo() throws YaHayUnPedidoEnCursoException{
        restauranteTest.iniciarPedido("Mateo", "direccion");
        assertNotNull(restauranteTest.getPedidoEnCurso());
    }

    @Test 
    void testIniciarPedidoRepetido() throws YaHayUnPedidoEnCursoException{
        restauranteTest.iniciarPedido("Mateo", "Direccion");
        assertThrows(YaHayUnPedidoEnCursoException.class, ()-> restauranteTest.iniciarPedido("Jose", "otra direccion"));
    }   
    @Test
    void testCerrarPedido() throws Exception{
        restauranteTest.iniciarPedido("Mateo", "direccion");
        restauranteTest.cerrarYGuardarPedido();
        assertNull(restauranteTest.getPedidoEnCurso());
    }

    @Test
    void testPedidosActualizadosEnLista() throws Exception{
        restauranteTest.iniciarPedido("Mateo", "direccion");
        restauranteTest.cerrarYGuardarPedido();
        assertEquals(1, restauranteTest.getPedidos().size());
    }

    @Test
    void testCerrarPedidoSinPedidos(){
        assertThrows(NoHayPedidoEnCursoException.class, ()->restauranteTest.cerrarYGuardarPedido());
    }

    @Test
    void testCargarInformacionRestaurante() throws Exception {
        File archivoIngredientes = File.createTempFile("ingredientes", ".txt");
        File archivoMenu = File.createTempFile("menu", ".txt");
        File archivoCombos = File.createTempFile("combos", ".txt");
        PrintWriter pwIngredientes = new PrintWriter(archivoIngredientes);
        pwIngredientes.println("lechuga;1000");
        pwIngredientes.close();
        PrintWriter pwMenu = new PrintWriter(archivoMenu);
        pwMenu.println("corral;14000");
        pwMenu.close();
        PrintWriter pwCombos = new PrintWriter(archivoCombos);
        pwCombos.println("combo corral;10%;corral");
        pwCombos.close();
        restauranteTest.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        assertEquals(1, restauranteTest.getIngredientes().size());
        assertEquals(1, restauranteTest.getMenuBase().size());
        assertEquals(1, restauranteTest.getMenuCombos().size());
    }

}