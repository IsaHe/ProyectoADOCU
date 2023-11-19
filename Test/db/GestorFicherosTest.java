package db;

import domain.Actividad;
import io.GestorFicheros;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GestorFicherosTest {
    Path rutaListaActividades;
    Path rutaActividadesSemanales;
    Actividad[][] actividadesSemanales = new Actividad[6][10];
    Map<String, Set<Actividad>> actividades;

    @Before
    public void setUp() {
        rutaListaActividades = Paths.get("Test/io/listaActividades.txt");
        rutaActividadesSemanales = Paths.get("Test/io/actividadesSemanales.txt");
    }

    @Test
    public void obtenerActividadesDeFichero() {

    }

    @Test
    public void cargarActividadesSemanalesEnFichero() {
    }

    @Test
    public void obtenerActividadesSemanalesDeFichero() {
    }

    @Test
    public void getActividades() {
    }

    @Test
    public void getActividadesSemanales() {
    }

    @Test
    public void setActividad() {
    }

    @Test
    public void setUltimoAccesoAtabla() {
    }

    @Test
    public void actualizarActividadesSemanales() {
    }
}