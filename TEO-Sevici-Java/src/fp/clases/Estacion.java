package fp.clases;

import java.util.Objects;

public class Estacion implements Comparable<Estacion> {
	private String id;
	private String nombre;
	private Integer numPuestos;
	private Integer bicisDisponibles;
	private Coordenadas ubicacion;
	
	public Estacion(String id,String nombre,Integer numPuestos,Integer bicisDisponibles,Coordenadas ubicacion) {
		if (numPuestos <= 0) {
			throw new IllegalArgumentException("El número de puestos debe ser mayor que 0");
		}
		if (!(bicisDisponibles >= 0 && bicisDisponibles <= numPuestos)) {
			throw new IllegalArgumentException("El número de bicicletas disponibles debe ser mayor o igual que 0 y menor o igual que el número de puestos");
		}
		this.id = id;
		this.nombre = nombre;
		this.numPuestos = numPuestos;
		this.bicisDisponibles = bicisDisponibles;
		this.ubicacion = ubicacion;
	}

	public Integer getBicisDisponibles() {
		return bicisDisponibles;
	}

	public void setBicisDisponibles(Integer bicisDisponibles) {
		if (!(bicisDisponibles >= 0 && bicisDisponibles <= numPuestos)) {
			throw new IllegalArgumentException("El número de bicicletas disponibles debe ser mayor o igual que 0 y menor o igual que el número de puestos");
		}
		this.bicisDisponibles = bicisDisponibles;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getNumPuestos() {
		return numPuestos;
	}

	public Coordenadas getUbicacion() {
		return ubicacion;
	}
	
	public Integer getPuestosVacios() {
		return (numPuestos - bicisDisponibles);
	}
	
	public Boolean getTieneBicisDisponibles() {
		Boolean res = false;
		if (bicisDisponibles >= 1) {
			res = true;
		}
		return res;
	}
	
	public Double getOcupacion() {
		return (bicisDisponibles/numPuestos) * 100.0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacion other = (Estacion) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(Estacion o) {
		int r;
		if (o==null) {
			throw new NullPointerException();
		}
		r = this.getId().compareTo(o.getId());
		return r;
	}

	@Override
	public String toString() {
		return "Estacion [id=" + id + ", nombre=" + nombre + ", numPuestos=" + numPuestos + ", bicisDisponibles="
				+ bicisDisponibles + ", ubicacion=" + ubicacion + "]";
	}
	
	
	
}
