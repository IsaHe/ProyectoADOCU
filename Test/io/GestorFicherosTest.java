package io;

import domain.Actividad;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GestorFicherosTest {
    Path rutaListaActividades;
    Path rutaActividadesSemanales;
    Map<String, Set<Actividad>> actividades;
    Actividad[][] actividadesSemanales;


    @Before
    public void setUp() {
        rutaListaActividades = Paths.get("Test/io/listaActividades.txt");
        rutaActividadesSemanales = Paths.get("Test/io/actividadesSemanales.txt");
    }

    @Test
    public void obtenerActividadesDeFichero() {
        GestorFicheros.obtenerActividadesDeFichero(rutaListaActividades);
        actividades = GestorFicheros.getActividades();

        assertEquals(7, actividades.size());
        String[] keys = {"a", "b", "c", "d", "e", "f", "g"};
        Actividad[][] Actividades = {
                {new Actividad("e", 1, 10.0f), new Actividad("f", 2, 20.0f)},
                {new Actividad("h", 1, 10.0f), new Actividad("i", 2, 20.0f)},
                {new Actividad("k", 1, 10.0f), new Actividad("l", 2, 20.0f)},
                {new Actividad("n", 1, 10.0f), new Actividad("o", 2, 20.0f)},
                {new Actividad("q", 1, 10.0f), new Actividad("r", 2, 20.0f)},
                {new Actividad("t", 1, 10.0f), new Actividad("u", 2, 30f)},
                {new Actividad("w", 1, 10.0f), new Actividad("x", 2, 30f)}
        };

        int i = 0;
        for (Map.Entry<String, Set<Actividad>> entry : actividades.entrySet()) {
            assertEquals(keys[i], entry.getKey());
            int j = 0;
            for (Actividad actividad : entry.getValue()) {
                assertEquals(Actividades[i][j], actividad);
                j++;
            }
            i++;
        }
    }

    @Test
    public void obtenerActividadesSemanalesDeFichero() {
        GestorFicheros.obtenerActividadesSemanalesDeFichero(rutaActividadesSemanales);
        actividadesSemanales = GestorFicheros.getActividadesSemanales();

        String[] nombres = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
        assertEquals(6, actividadesSemanales.length);

        int i = 0;
        for (Actividad[] actividades : actividadesSemanales) {
            assertEquals(10, actividades.length);
            for (Actividad actividad : actividades) {
                if (actividad == null) {continue;}
                assertEquals(nombres[i], actividad.getNombre());
                i++;
            }
        }
    }

    @Test
    public void setActividad() {
        GestorFicheros.obtenerActividadesSemanalesDeFichero(rutaActividadesSemanales);

        GestorFicheros.setActividad(new Actividad("a", 1, 10.0f), 0, 0);
        assertEquals("a", GestorFicheros.getActividadesSemanales()[0][0].getNombre());

        GestorFicheros.setActividad(new Actividad("b", 1, 10.0f), 0, 1);
        assertEquals("b", GestorFicheros.getActividadesSemanales()[0][1].getNombre());

        GestorFicheros.setActividad(new Actividad("c", 1, 10.0f), 5, 9);
        assertEquals("c", GestorFicheros.getActividadesSemanales()[5][9].getNombre());
    }

    @Test
    public void setUltimoAccesoAtabla() {
        GestorFicheros.setUltimoAccesoAtabla(LocalDate.now());
        assertEquals(LocalDate.now(), GestorFicheros.getUltimoAccesoAtabla());

        GestorFicheros.setUltimoAccesoAtabla(LocalDate.of(2020, 1, 1));
        assertEquals(LocalDate.of(2020, 1, 1), GestorFicheros.getUltimoAccesoAtabla());

        GestorFicheros.setUltimoAccesoAtabla(LocalDate.of(2020, 12, 31));
        assertEquals(LocalDate.of(2020, 12, 31), GestorFicheros.getUltimoAccesoAtabla());
    }
}