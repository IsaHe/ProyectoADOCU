package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UsuarioTest {

    Usuario usuario;
    Usuario usuario1;
    Usuario usuario2;

    @Before
    public void setUp(){
        usuario = new Usuario();
        usuario1 = new Usuario("nombre", "apellido", 19, "usuario", "contraseña", null);
        usuario2 = new Usuario("usuario", "contraseña");
    }

    @Test
    public void testSetlActividades() {
        ArrayList<Actividad> lActividades = new ArrayList<>();

        usuario.setlActividades(lActividades);
        assertEquals(lActividades, usuario.getlActividades());
        assertNull(usuario1.getlActividades());
        lActividades.add(new Actividad());
        usuario1.setlActividades(lActividades);
        assertEquals(lActividades, usuario1.getlActividades());
    }

    @Test
    public void testSetNom() {
        usuario1.setNom("nombre");
        assertEquals("nombre", usuario1.getNom());
        usuario2.setNom("nombr2121e");
        assertEquals("nombr2121e", usuario2.getNom());
        assertNull(usuario.getNom());
    }

    @Test
    public void testSetApellido() {
        usuario1.setApellido("apellido");
        assertEquals("apellido", usuario1.getApellido());
        usuario2.setApellido("apellid2121e");
        assertEquals("apellid2121e", usuario2.getApellido());
        assertNull(usuario.getApellido());
    }

    @Test
    public void testSetEdad() {
        usuario1.setEdad(19);
        assertEquals(19, usuario1.getEdad());
        usuario2.setEdad(20);
        assertEquals(20, usuario2.getEdad());
        assertEquals(0, usuario.getEdad());
    }

    @Test
    public void testSetUsuario() {
        usuario1.setUsuario("usuario");
        assertEquals("usuario", usuario1.getUsuario());
        usuario2.setUsuario("usuari2121o");
        assertEquals("usuari2121o", usuario2.getUsuario());
        assertNull(usuario.getUsuario());
    }

    @Test
    public void testSetContraseña() {
        usuario1.setContraseña("contraseña");
        assertEquals("contraseña", usuario1.getContraseña());
        usuario2.setContraseña("contraseñ2121a");
        assertEquals("contraseñ2121a", usuario2.getContraseña());
        assertNull(usuario.getContraseña());
    }

    @Test
    public void testEliminarActividadDeLista() {
        ArrayList<Actividad> lActividades = new ArrayList<>();
        Actividad actividad = new Actividad();
        lActividades.add(actividad);
        usuario1.setlActividades(lActividades);
        usuario1.eliminarActividadDeLista(actividad);
        assertEquals(0, usuario1.getlActividades().size());
    }

    @Test
    public void testTestToString() {
        assertEquals("nombre;apellido;19;usuario;contraseña", usuario1.toString());
        assertEquals("null;null;0;usuario;contraseña", usuario2.toString());
        assertEquals("null;null;0;null;null", usuario.toString());
    }

    @Test
    public void testTestEquals() {
        assertEquals(usuario, new Usuario());
        assertEquals(usuario1, new Usuario("nombre", "apellido", 19, "usuario", "contraseña", null));
        assertEquals(usuario2, new Usuario("usuario", "contraseña"));
        assertNotEquals(usuario2, new Usuario("usuario151", "contraseñ"));
    }
}