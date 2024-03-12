package fp.clases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.clases.Estacion;

public class FactoriaRedEstaciones {
	public static RedEstaciones leerRedEstaciones(String nombreFichero, String nombreRed) {
		List<Estacion> res = new ArrayList<>();
		try {
			List<String> lineas = Files.readAllLines(Paths.get(nombreFichero));
			for(String linea:lineas.subList(1, lineas.size())) { // Saltar la primera linea
				Estacion e = parsearEstacion(linea);
				res.add(e);
			}
		}
		catch(IOException e) {
			System.out.println("No se ha encontrado el fichero " + nombreFichero);
		}
		return res;
	}
	public static Estacion parsearEstacion(String lineaCSV) {
		String[] trozos = lineaCSV.split(",");
		
		String id = parseaNombre(trozos[0].trim()).first();
		String nombre = parseaNombre(trozos[0].trim()).last();
		Integer numPuestos = Integer.valueOf(trozos[1]);
		Integer bicisDisponibles = Integer.valueOf(trozos[3]);
		Double latitud = Double.valueOf(trozos[4]);
		Double longitud = Double.valueOf(trozos[5]);
		
		return new Estacion(id,nombre,numPuestos,bicisDisponibles,new Coordenadas(latitud,longitud));
	}
	private static SortedSet<String> parseaNombre(String cad) {
		SortedSet<String> res = new TreeSet<String>();
		String[] trozos = cad.split("_");
		for (String trozo:trozos) {
			res.add(trozo);
		}
		return res;
	}
}
