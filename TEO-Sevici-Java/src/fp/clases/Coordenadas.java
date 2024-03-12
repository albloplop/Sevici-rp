package fp.clases;

public record Coordenadas(Double latitud, Double longitud, UnidadMedida unidad) {
	public Coordenadas() {
		this(0.0, 0.0);
	}
	
	public Coordenadas(Double latitud, Double longitud) {
		this(latitud, longitud, UnidadMedida.GRADOS);
		
		if (latitud < -90 || latitud > 90) {
			throw new IllegalArgumentException("La latitud debe estar comprendida entre -90º y +90º");
		}
		if (longitud < -180 || longitud > 180) {
			throw new IllegalArgumentException("La longitud debe estar comprendida entre -180º y +180º");
		}
	}
	
	public Coordenadas {
		if (latitud < -90 || latitud > 90) {
			throw new IllegalArgumentException("La latitud debe estar comprendida entre -90º y +90º");
		}
		if (longitud < -180 || longitud > 180) {
			throw new IllegalArgumentException("La longitud debe estar comprendida entre -180º y +180º");
		}
	}
	
	public Coordenadas(String s) {
		String[] arr = s.split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lon = Double.valueOf(arr[1]);
		this(lat, lon, UnidadMedida.GRADOS);
		
		if (Double.valueOf(arr[0]) < -90 || Double.valueOf(arr[0]) > 90) {
			throw new IllegalArgumentException("La latitud debe estar comprendida entre -90º y +90º");
		}
		if (Double.valueOf(arr[1]) < -180 || Double.valueOf(arr[1]) > 180) {
			throw new IllegalArgumentException("La longitud debe estar comprendida entre -180º y +180º");
		}
	}
	
	public Double getDistanciaHaversine (Coordenadas c) {
		double R = 6371.0; // Radio de la Tierra en kilómetros

        // Convertir grados a radianes
        double lat1Rad = aRadianes(this.latitud());
        double lon1Rad = aRadianes(this.longitud());
        double lat2Rad = aRadianes(c.latitud());
        double lon2Rad = aRadianes(c.longitud());

        // Diferencias de latitud y longitud
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Fórmula de Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c2 = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = R * c2; // Distancia en kilómetros

        return distancia;
	}
	
	public Coordenadas aRadianes() {
		double pi = Math.PI;
		if (unidad == UnidadMedida.RADIANES) {
			return new Coordenadas(latitud, longitud, UnidadMedida.RADIANES);
	       	} 
		else {
			double latitudRad = latitud * (2*pi/180);
		 	double longitudRad = longitud * (2*pi/180);
			return new Coordenadas(latitudRad, longitudRad, UnidadMedida.RADIANES);
		 	}
	}
	
	public Coordenadas aGrados() {
		double pi = Math.PI;
		if (unidad == UnidadMedida.RADIANES) {
			return new Coordenadas(latitud, longitud, UnidadMedida.GRADOS);
	       	} 
		else {
			double latitudRad = latitud * (180/2*pi);
		 	double longitudRad = longitud * (180/2*pi);
			return new Coordenadas(latitudRad, longitudRad, UnidadMedida.GRADOS);
		 	}
	}
}
