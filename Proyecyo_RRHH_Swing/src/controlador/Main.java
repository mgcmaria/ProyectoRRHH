package controlador;

import vista.Ventana;

public class Main {

	public static void main(String[] args) {

		Ventana miVentana = new Ventana();//Creamos el objeto ventana
		Eventos manejador = new Eventos(miVentana);// Creamos el objeto manejador de la clase Eventos,
		//pasando el parametro miVentana como argumento
		miVentana.Eventos(manejador); //Llamamos a la funcion Eventos desde el objeto miVentana

	}

}
