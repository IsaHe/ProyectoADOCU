package domain;

import io.GestorFicheros;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ActividadTest {

    Actividad a1;
    Actividad a2;
    Actividad a3;
    Actividad a4;
    Actividad a5;
    Actividad a6;

    Actividad a7;
    Actividad a8;
    Actividad a9;
    Actividad a10;
    Actividad a11;
    Actividad a12;

    Actividad[][] arrayActividades;

    @Before
    public void setUp() {
        a1 = new Actividad();
        a2 = new Actividad("a", 1, 1.005F);
        a3 = new Actividad("a", 582, 15000.8548F, false, "usuario");
        a4 = new Actividad("b", 41, 878.434343F, true, "usuario");
        a5 = new Actividad("a", 240, 30.342323F, false, "usuario");
        a6 = new Actividad("a", 147, 10.454874548724548F, true, "usuario");

        a7 = new Actividad();
        a8 = new Actividad("a", 1, 1.005F);
        a9 = new Actividad("a", 582, 15000.8548F);
        a10 = new Actividad("b", 41, 878.434343F);
        a11 = new Actividad("a", 240, 30.342323F);
        a12 = new Actividad("a", 147, 10.454874548724548F);

    }

    @Test
    public void getPrecio() {
        assertEquals(0, a1.getPrecio(), 0);
        assertEquals(1.005F, a2.getPrecio(), 0);
        assertEquals(15000.8548F, a3.getPrecio(), 0);
        assertEquals(878.434343F, a4.getPrecio(), 0);
        assertEquals(30.342323F, a5.getPrecio(), 0);
        assertEquals(10.454874548724548F, a6.getPrecio(), 0);
    }

    @Test
    public void setPrecio() {
        a1.setPrecio(1.005F);
        assertEquals(1.005F, a1.getPrecio(), 0);
        a2.setPrecio(15000.8548F);
        assertEquals(15000.8548F, a2.getPrecio(), 0);
        a3.setPrecio(878.434343F);
        assertEquals(878.434343F, a3.getPrecio(), 0);
        a4.setPrecio(30.342323F);
        assertEquals(30.342323F, a4.getPrecio(), 0);
        a5.setPrecio(10.454874548724548F);
        assertEquals(10.454874548724548F, a5.getPrecio(), 0);
        a6.setPrecio(0);
        assertEquals(0, a6.getPrecio(), 0);
    }

    @Test
    public void getNombre() {
        assertNull(a1.getNombre());
        assertEquals("a", a2.getNombre());
        assertEquals("a", a3.getNombre());
        assertEquals("b", a4.getNombre());
        assertEquals("a", a5.getNombre());
        assertEquals("a", a6.getNombre());
    }

    @Test
    public void setNombre() {
        a1.setNombre("a");
        assertEquals("a", a1.getNombre());
        a2.setNombre("b");
        assertEquals("b", a2.getNombre());
        a3.setNombre("c");
        assertEquals("c", a3.getNombre());
        a4.setNombre("d");
        assertEquals("d", a4.getNombre());
        a5.setNombre("e");
        assertEquals("e", a5.getNombre());
        a6.setNombre("f");
        assertEquals("f", a6.getNombre());
    }

    @Test
    public void getNumeroParticipantes() {
        assertEquals(0, a1.getNumeroParticipantes());
        assertEquals(1, a2.getNumeroParticipantes());
        assertEquals(582, a3.getNumeroParticipantes());
        assertEquals(41, a4.getNumeroParticipantes());
        assertEquals(240, a5.getNumeroParticipantes());
        assertEquals(147, a6.getNumeroParticipantes());
    }

    @Test
    public void setNumeroParticipantes() {
        a1.setNumeroParticipantes(1);
        assertEquals(1, a1.getNumeroParticipantes());
        a2.setNumeroParticipantes(582);
        assertEquals(582, a2.getNumeroParticipantes());
        a3.setNumeroParticipantes(41);
        assertEquals(41, a3.getNumeroParticipantes());
        a4.setNumeroParticipantes(240);
        assertEquals(240, a4.getNumeroParticipantes());
        a5.setNumeroParticipantes(147);
        assertEquals(147, a5.getNumeroParticipantes());
        a6.setNumeroParticipantes(0);
        assertEquals(0, a6.getNumeroParticipantes());
    }

    @Test
    public void toStringBd() {
        assertEquals("null,0,0.0,false,null", a1.toStringBd());
        assertEquals("a,1,1.005,false,null", a2.toStringBd());
        assertEquals("a,582,15000.8545,false,usuario", a3.toStringBd());
        assertEquals("b,41,878.4343,true,usuario", a4.toStringBd());
        assertEquals("a,240,30.342323,false,usuario", a5.toStringBd());
        assertEquals("a,147,10.454875,true,usuario", a6.toStringBd());
    }

    @Test
    public void shiftArray() {
        GestorFicheros.obtenerActividadesSemanalesDeFichero(Paths.get("Test/io/ActividadesSemanales.txt"));
        arrayActividades = GestorFicheros.getActividadesSemanales();
        Actividad[][] shiftedArray = Actividad.shiftArray(arrayActividades);

        assertArrayEquals(arrayActividades[1], shiftedArray[0]);
        assertArrayEquals(arrayActividades[2], shiftedArray[1]);
        assertArrayEquals(arrayActividades[3], shiftedArray[2]);
        assertArrayEquals(arrayActividades[4], shiftedArray[3]);
        assertArrayEquals(arrayActividades[5], shiftedArray[4]);
        assertArrayEquals(new Actividad[10], shiftedArray[5]);

    }

    @Test
    public void testToString() {
        assertNull(a1.toString());
        assertEquals("a", a2.toString());
        assertEquals("a", a3.toString());
        assertEquals("b", a4.toString());
        assertEquals("a", a5.toString());
        assertEquals("a", a6.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(a1, new Actividad());
        assertEquals(a2, new Actividad("a", 1, 1.005F));
        assertEquals(a3, new Actividad("a", 582, 15000.8548F, false, "usuario"));
        assertNotEquals(a4, new Actividad("h", 41, 878.434343F, true, "usuario"));
        assertNotEquals(a5, new Actividad("g", 240, 30.342323F, false, "usuario"));
    }

    @Test
    public void testSetPagada() {
        	a1.setPagada(true);
        	assertTrue(a1.isPagada());
        	a2.setPagada(true);
        	assertTrue(a2.isPagada());
        	a3.setPagada(true);
        	assertTrue(a3.isPagada());
        	a4.setPagada(false);
        	assertFalse(a4.isPagada());
        	a5.setPagada(true);
        	assertTrue(a5.isPagada());
        	a6.setPagada(false);
        	assertFalse(a6.isPagada());
    }

    @Test
    public void testSetUsuario() {
        	a1.setUsuario("usuario1");
        	assertEquals("usuario1", a1.getUsuario());
        	a2.setUsuario("usuario1815");
        	assertEquals("usuario1815", a2.getUsuario());
        	a3.setUsuario("usuario4815");
        	assertEquals("usuario4815", a3.getUsuario());
        	a4.setUsuario("usu485ario");
        	assertEquals("usu485ario", a4.getUsuario());
        	a5.setUsuario("usu79779ario");
        	assertEquals("usu79779ario", a5.getUsuario());
        	a6.setUsuario("usuari1515o");
        	assertEquals("usuari1515o", a6.getUsuario());
    }

    @Test
    public void testToStringAdmin() {
        	assertEquals("Actividad: null - Usuario: null", a1.toStringAdmin());
            assertEquals("Actividad: a - Usuario: null", a2.toStringAdmin());
            assertEquals("Actividad: a - Usuario: usuario", a3.toStringAdmin());
    }
}