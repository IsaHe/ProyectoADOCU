package domain;

import io.GestorFicheros;
import org.junit.Assert;
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

    Actividad[][] arrayActividades;

    @Before
    public void setUp() {
        a1 = new Actividad();
        a2 = new Actividad("a", 1, 1.005F);
        a3 = new Actividad("a", 582, 15000.8548F);
        a4 = new Actividad("b", 41, 878.434343F);
        a5 = new Actividad("a", 240, 30.342323F);
        a6 = new Actividad("a", 147, 10.454874548724548F);
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
        assertEquals("null,0,0.0", a1.toStringBd());
        assertEquals("a,1,1.005", a2.toStringBd());
        assertEquals("a,582,15000.8545", a3.toStringBd());
        assertEquals("b,41,878.4343", a4.toStringBd());
        assertEquals("a,240,30.342323", a5.toStringBd());
        assertEquals("a,147,10.454875", a6.toStringBd());
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
        Actividad a7 = new Actividad();
        Actividad a8 = new Actividad("a", 1, 1.005F);
        Actividad a9 = new Actividad("a", 582, 15000.8548F);
        Actividad a10 = new Actividad("b", 41, 878.434343F);
        Actividad a11 = new Actividad("a", 240, 30.342323F);
        Actividad a12 = new Actividad("a", 147, 10.454874548724548F);

        Assert.assertEquals(a1, a7);
        Assert.assertEquals(a2, a8);
        Assert.assertEquals(a3, a9);
        Assert.assertEquals(a4, a10);
        Assert.assertEquals(a5, a11);
        Assert.assertEquals(a6, a12);
    }
}