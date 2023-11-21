package io;

import domain.Actividad;
import domain.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class GestorFicheros {
    private static final Map<String, Set<Actividad>> actividades = new HashMap<>();
    private static Actividad[][] actividadesSemanales = new Actividad[6][10];
    private static LocalDate ultimoAccesoAtabla;
    private static Map<String, ArrayList<Actividad>> mapaActividadesUsuario = new HashMap<>();

    static Logger logger = Logger.getLogger(GestorFicheros.class.getName());


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
                    Actividad actividad = new Actividad(partesActividad[0], Integer.parseInt(partesActividad[1]), Float.parseFloat(partesActividad[2]));
                    listaActividades.add(actividad);
                }
                actividades.put(dia, listaActividades);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.warning("Error al obtener las actividades del fichero");
        }

    }

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
                        Actividad actividad = new Actividad(partesActividad[0], Integer.parseInt(partesActividad[1]),Float.parseFloat(partesActividad[2]));
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

    public static Map<String, Set<Actividad>> getActividades() {
        return actividades;
    }

    public static Actividad[][] getActividadesSemanales() {
        return actividadesSemanales;
    }

    public static void setActividad(Actividad a, int i, int j) {
        actividadesSemanales[i][j] = a;
    }

    public static LocalDate getUltimoAccesoAtabla() {
        return ultimoAccesoAtabla;
    }

    public static void setUltimoAccesoAtabla(LocalDate ultimoAccesoAtabla) {
        GestorFicheros.ultimoAccesoAtabla = ultimoAccesoAtabla;
    }

    public static void actualizarActividadesSemanales() {
        if (ultimoAccesoAtabla.getDayOfYear() < LocalDate.now().getDayOfYear()) {
            for (int i = 0; i < LocalDate.now().getDayOfYear() - ultimoAccesoAtabla.getDayOfYear(); i++) {
                actividadesSemanales = Actividad.shiftArray(actividadesSemanales);
            }
        }
    }	
    
    public static void obtenerActividadesUsuarioEnFicheroBinario (Usuario u, Path ruta) {
    	
    	try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()));
			mapaActividadesUsuario = (Map<String, ArrayList<Actividad>>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	mapaActividadesUsuario.putIfAbsent(u.getUsuario(), new ArrayList<>());
    	u.setlActividades(mapaActividadesUsuario.get(u.getUsuario()));
    	
    }
    
    public static void cargarActividadesUsuarioEnFicheroBinario (Usuario u, Path ruta) {
    	
    	mapaActividadesUsuario.putIfAbsent(u.getUsuario(), new ArrayList<>());
    	
    	try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()));
			oos.writeObject(mapaActividadesUsuario);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
