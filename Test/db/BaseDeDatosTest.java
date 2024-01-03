package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Usuario;

public class BaseDeDatosTest {

	List<Usuario> usuarios;
	List<Integer> valoraciones;
	Connection con = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
	
	@Before
	public void setUp() {
		
		BaseDeDatos.obtenerUsuariosDeBaseDeDatos(con);
		valoraciones = BaseDeDatos.getValoraciones();
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
	
	@Test
	public void  getValoracionesTest() {
		assertEquals(valoraciones, BaseDeDatos.getValoraciones());
	}
	
	@Test
	public void obtenerUsuariosDeBaseDeDatosTest () {
		
		assertThrows(AssertionError.class, ()->{
			assertThrows(Exception.class, ()->{
				BaseDeDatos.obtenerUsuariosDeBaseDeDatos(con);
			});
		});
	}
	
	@Test
	public void  cargarUsuariosEnBaseDeDatosTest() {
		assertThrows(AssertionError.class, ()->{
			assertThrows(Exception.class, ()->{
				BaseDeDatos.cargarUsuariosEnBaseDeDatos(con);
			});
		});
	}
	
	@Test
	public void iniciarBaseDeDatosValoracionesTest() {
		assertNotNull(BaseDeDatos.iniciarBaseDeDatos("src/db/valoraciones.db"));
		
	}
	
	@Test
	public void cargarValoracionesEnBaseDeDatosTest() {
		assertThrows(AssertionError.class, ()->{
			assertThrows(Exception.class, ()->{
				BaseDeDatos.cargarValoracionEnBaseDeDatos(con);
			});
		});
	}
	
	@Test
	public void obtenerValoracionesDeBaseDeDatosTest() {
		assertThrows(AssertionError.class, ()->{
			assertThrows(Exception.class, ()->{
				BaseDeDatos.obtenerValoracionesDeBaseDeDatos(con);
			});
		});
	}
	
	
}
