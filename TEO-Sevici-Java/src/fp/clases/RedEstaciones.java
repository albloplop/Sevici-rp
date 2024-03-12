package fp.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class RedEstaciones {
	private String nombre;
	private SortedSet<Estacion> estaciones;
	
	public RedEstaciones() {
		this.nombre = "";
	}
	
	public RedEstaciones(String nombre) {
		this.nombre = nombre;
		this.estaciones = new TreeSet<>();
	}
	
	public RedEstaciones(String nombre, Set<Estacion> estaciones) {
		this.nombre = nombre;
		this.estaciones = new TreeSet<Estacion>(estaciones);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SortedSet<Estacion> getEstaciones() {
		return new TreeSet<Estacion>();
	}
	
	public Integer getNumeroEstaciones() {
		return estaciones.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedEstaciones other = (RedEstaciones) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	public void agregarEstacion(Estacion e) {
		if (!estaciones.contains(e)) {
			estaciones.add(e);
		}
	}
	
	public List<Estacion> getEstacionesBicisDisponibles() {
		List<Estacion> res = new ArrayList<>();
		for (Estacion e:estaciones) {
			if (e.getTieneBicisDisponibles()) {
				res.add(e);
			}
		}
		return res;
	}
	
	public List<Estacion> getEstacionesBicisDisponibles(Integer k) {
		List<Estacion> res = new ArrayList<>();
		for (Estacion e:estaciones) {
			if (e.getBicisDisponibles() >= k) {
				res.add(e);
			}
		}
		return res;
	}
	
	public SortedSet<Estacion> getEstacionesCercanas(Coordenadas cs, Double distancia) {
		SortedSet<Estacion> res = new TreeSet<Estacion>();
		for (Estacion e:estaciones) {
			if (cs.getDistanciaHaversine(e.getUbicacion()) <= distancia) {
				res.add(e);
			}
		}
		return res;
	}
	
	public Estacion getEstacionMasBicisDisponibles() {
		Estacion maxEstacion = null;
		for (Estacion e:estaciones) {
			if (maxEstacion == null || e.getBicisDisponibles() > maxEstacion.getBicisDisponibles()) {
				maxEstacion = e;
			}
		}
		return maxEstacion;
	}
	
	public Double getOcupacionMediaEstacionesConBicis() {
		Double suma = 0.0;
		Integer numElem = 0;
		for (Estacion e:estaciones) {
			if (e.getTieneBicisDisponibles()) {
				suma += e.getOcupacion();
				numElem += 1;
			}
		}
		Double res = null;
		if (numElem > 0) {
			res = suma/numElem;
		}
		return res;
	}
	
	public Boolean tienenBicisDisponiblesTodasEstacionesCercanas(Coordenadas cs, Double distancia) {
		Boolean res = true;
		for (Estacion e:estaciones) {
			if (cs.getDistanciaHaversine(e.getUbicacion()) <= distancia && !e.getTieneBicisDisponibles()) {
				res = false;
			}
		}
		return res;
	}
} 
