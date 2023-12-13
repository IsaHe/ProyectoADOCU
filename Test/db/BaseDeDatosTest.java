package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Usuario;

public class BaseDeDatosTest {

	List<Usuario> usuarios;
	Connection con = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
	
	@Before
	public void setUp() {
		
		BaseDeDatos.obtenerUsuariosDeBaseDeDatos(con);
		
		usuarios = BaseDeDatos.getUsuarios();
	}
	
	@Test
	public void comprobarUsuarioTest() {
		Usuario u = new Usuario("Isaac", "Herbozo", 19, "Isaac", "111", null);
		assertEquals(true, usuarios.contains(u));
	}
	
	@Test
	public void getUsuariosTest() {
		assertEquals(usuarios, BaseDeDatos.getUsuarios());
	}
	
	@Test
	public void iniciarBaseDeDatosTest() {
		assertNotNull(BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db"));
	}
	
	
}
