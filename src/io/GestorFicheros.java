package io;

import domain.Actividad;
import domain.Usuario;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * GestorFicheros es una clase que se encarga de la gestión de ficheros en la aplicación.
 * Esta clase contiene métodos para leer y escribir actividades en ficheros, y para manipular las actividades en la memoria.
 * <p>
 * La clase contiene varias estructuras de datos para almacenar las actividades, como un Map para las actividades por día, un array bidimensional para las actividades semanales, y un Map para las actividades por usuario.
 * También contiene un Logger para registrar información y errores, y una variable LocalDate para almacenar la última vez que se accedió a la tabla de actividades.
 * <p>
 * Los métodos de la clase permiten obtener las actividades de un fichero, cargar las actividades semanales en un fichero, obtener las actividades semanales de un fichero, cambiar el estado de pago de una actividad, asignar un usuario a una actividad, eliminar una actividad de la tabla de actividades, obtener y establecer la última vez que se accedió a la tabla de actividades, actualizar las actividades semanales, obtener las actividades de un usuario de un fichero binario, cargar las actividades de un usuario en un fichero binario, eliminar una actividad de un usuario del mapa de actividades, y eliminar y guardar las actividades de un usuario del mapa de actividades.
 */
public class GestorFicheros {
    private static final Map<String, Set<Actividad>> actividades = new HashMap<>();
    private static Actividad[][] actividadesSemanales = new Actividad[6][10];
    private static LocalDate ultimoAccesoAtabla;
    private static Map<String, ArrayList<Actividad>> mapaActividadesUsuario = new HashMap<>();

    static final Logger logger = Logger.getLogger(GestorFicheros.class.getName());

    /**
     * Método para obtener las actividades de un fichero.
     * @param ruta La ruta del fichero.
     */
    public static void obtenerActividadesDeFichero (Path ruta) {

        try {
            Scanner sc = new Scanner(new FileInputStream(ruta.toFile()));
            while (sc.hasNext()) {
                Set<Actividad> listaActividades = new HashSet<>();
                String linea = sc.next();
                String[] partes = linea.split(";");
                String dia = partes[0];
                for (int i = 1; i < partes.length; i++) {
                    String[] partesActividad = partes[i].split(",");
                    Actividad actividad = new Actividad(partesActividad[0], Integer.parseInt(partesActividad[1]), Float.parseFloat(partesActividad[2]), false, null);
                    listaActividades.add(actividad);
                }
                actividades.put(dia, listaActividades);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.warning("Error al obtener las actividades del fichero");
        }

    }

    /**
     * Método para cargar las actividades semanales en un fichero.
     * @param ruta La ruta del fichero.
     */
    public static void cargarActividadesSemanalesEnFichero(Path ruta) {

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(ruta.toFile()));
            pw.write(ultimoAccesoAtabla.toString() + "\n");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 10; j++) {
                    if (actividadesSemanales[i][j] != null) {
                        pw.write(actividadesSemanales[i][j].toStringBd());
                    }
                    pw.write(";");
                }
                pw.write("\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            logger.warning("Error al cargar las actividades semanales al fichero");
        }
    }

    /**
     * Método para obtener las actividades semanales de un fichero.
     * @param ruta La ruta del fichero.
     */
    public static void obtenerActividadesSemanalesDeFichero(Path ruta) {

        actividadesSemanales = new Actividad[6][10];
        try {
            Scanner sc = new Scanner(new FileInputStream(ruta.toFile()));
            ultimoAccesoAtabla = LocalDate.parse(sc.next());
            int i = 0;
            while (sc.hasNext()) {
                String linea = sc.next();
                String[] partes = linea.split(";");
                for (int j = 0; j < partes.length; j++) {
                    if (!partes[j].isEmpty()) {
                        String[] partesActividad = partes[j].split(",");
                        Actividad actividad = new Actividad(partesActividad[0], Integer.parseInt(partesActividad[1]),Float.parseFloat(partesActividad[2]),Boolean.parseBoolean(partesActividad[3]) , partesActividad[4]);
                        actividadesSemanales[i][j] = actividad;
                    }
                }
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.warning("Error al obtener las actividades semanales del fichero");
        }

    }

    /**
     * Método para cambiar el estado de pago de una actividad semanal.
     * @param a La actividad.
     */
    public static void cambiarEstadoDePagoEnActividadSemanal (Actividad a) {
    	 for (int i = 0; i < 6; i++) {
             for (int j = 0; j < 10; j++) {
            	 if (actividadesSemanales[i][j] == a) {
            		 actividadesSemanales[i][j].setPagada(true);
            	 }
             }
         }
                 
    }

    /**
     * Método para asignar un usuario a una actividad semanal.
     * @param a La actividad.
     * @param usuario El usuario.
     */
    public static void asignarUsuarioAActividadSemanal (Actividad a, String usuario) {
    	for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
           	 if (actividadesSemanales[i][j] == a) {
           		 actividadesSemanales[i][j].setUsuario(usuario);
           	 }
         }
      }
    }

    /**
     * Método para eliminar una actividad de la actividad semanal.
     * @param a La actividad.
     */
    public static void eliminarActividadDeActividadSemanal (Actividad a) {
    	for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
           	 	if (actividadesSemanales[i][j] == a) {
           	 		actividadesSemanales[i][j] = null;
           	 	}
         }
      }
    }

    /**
     * Método para obtener las actividades.
     * @return Las actividades.
     */
    public static Map<String, Set<Actividad>> getActividades() {
        return actividades;
    }

    /**
     * Método para obtener las actividades semanales.
     * @return Las actividades semanales.
     */
    public static Actividad[][] getActividadesSemanales() {
        return actividadesSemanales;
    }

    /**
     * Método para establecer una actividad.
     * @param a La actividad.
     * @param i El índice i.
     * @param j El índice j.
     */
    public static void setActividad(Actividad a, int i, int j) {
        actividadesSemanales[i][j] = a;
    }

    /**
     * Método para obtener la última vez que se accedió a la tabla.
     * @return La última vez que se accedió a la tabla.
     */
    public static LocalDate getUltimoAccesoAtabla() {
        return ultimoAccesoAtabla;
    }

    /**
     * Método para establecer la última vez que se accedió a la tabla.
     * @param ultimoAccesoAtabla La última vez que se accedió a la tabla.
     */
    public static void setUltimoAccesoAtabla(LocalDate ultimoAccesoAtabla) {
        GestorFicheros.ultimoAccesoAtabla = ultimoAccesoAtabla;
    }

    /**
     * Método para actualizar las actividades semanales.
     */
    public static void actualizarActividadesSemanales() {
        if (ultimoAccesoAtabla.getDayOfYear() < LocalDate.now().getDayOfYear()) {
            for (int i = 0; i < LocalDate.now().getDayOfYear() - ultimoAccesoAtabla.getDayOfYear(); i++) {
                actividadesSemanales = Actividad.shiftArray(actividadesSemanales);
            }
        }
    }

    /**
     * Método para obtener las actividades de un usuario de un fichero binario.
     * @param u El usuario.
     * @param ruta La ruta del fichero.
     */
	public static void obtenerActividadesUsuarioEnFicheroBinario (Usuario u, Path ruta) {
    	
    	try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()));
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                mapaActividadesUsuario = (Map<String, ArrayList<Actividad>>) obj;
            } else {
                logger.warning("Error: el objeto leído del fichero no es un Map<String, ArrayList<Actividad>>");
            }
            ois.close();
		} catch (IOException | ClassNotFoundException e) {
            logger.warning("Error al obtener las actividades del usuario del fichero");
            JOptionPane.showMessageDialog(null, "Error al obtener las actividades del usuario del fichero", "Error", JOptionPane.ERROR_MESSAGE);
		}
        mapaActividadesUsuario.putIfAbsent(u.getUsuario(), new ArrayList<>());
    	u.setlActividades(mapaActividadesUsuario.get(u.getUsuario()));
    }

    /**
     * Método para cargar las actividades de un usuario en un fichero binario.
     * @param u El usuario.
     * @param ruta La ruta del fichero.
     */
    public static void cargarActividadesUsuarioEnFicheroBinario (Usuario u, Path ruta) {
    	mapaActividadesUsuario.putIfAbsent(u.getUsuario(), new ArrayList<>());
    	
    	try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()));
			oos.writeObject(mapaActividadesUsuario);
			oos.close();
		} catch (IOException e) {
			logger.warning("Error al cargar las actividades del usuario al fichero");
            JOptionPane.showMessageDialog(null, "Error al cargar las actividades del usuario al fichero", "Error", JOptionPane.ERROR_MESSAGE);
		}
    }

    /**
     * Método para eliminar una actividad de un usuario del mapa de actividades.
     * @param usuario El usuario.
     * @param a La actividad.
     */
    public static void eliminarActividadUsuarioDeMapa (String usuario, Actividad a){
    	ArrayList<Actividad> actividades = mapaActividadesUsuario.get(usuario);
    	actividades.remove(a);
    }

    /**
     * Método para eliminar y guardar las actividades de un usuario del mapa de actividades.
     * @param usuario El usuario.
     * @param a La actividad.
     */
    public static void eliminarYGuardarActividadesUsuarioDeMapa (String usuario, Actividad a){
        eliminarActividadDeActividadSemanal(a);
        cargarActividadesSemanalesEnFichero(Paths.get("src/io/ActividadesSemanales.txt"));
        eliminarActividadUsuarioDeMapa(usuario, a);
        cargarActividadesUsuarioEnFicheroBinario2(usuario, Paths.get("src/io/ActividadesUsuario.dat"));
    }

    /**
     * Método para cargar las actividades de un usuario en un fichero binario.
     * @param u El usuario.
     * @param ruta La ruta del fichero.
     */
     public static void cargarActividadesUsuarioEnFicheroBinario2 (String u, Path ruta) {

            mapaActividadesUsuario.putIfAbsent(u, new ArrayList<>());

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()));
                oos.writeObject(mapaActividadesUsuario);
                oos.close();
            } catch (IOException e) {
                logger.warning("Error al cargar las actividades del usuario al fichero binario2");
                JOptionPane.showMessageDialog(null, "Error al cargar las actividades del usuario al fichero", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

}
