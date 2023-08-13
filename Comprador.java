/*Marco Antonio Carbajal Bonilla (23025)
Guatemala, Universidad del Valle de Guatemala
Programación Orientada a Objetos; Sección 60
Ejercicio 1: Overloading y relaciones entre clases
Creación: 08 de agosto del 2023
Última modificación: 13 de agosto del 2023*/

package tomorrowland;

class Comprador {//Esta clase contendrá la información de los compradores
	//Estas son las variables que almacenarán la información del comprador activo
	private String nombre;
	private int cantidad_boletos, presupuesto;
	private long DPI;
	
	public Comprador() {//Constructor

	}

	public int getCantidad_boletos() {//Obtener la cantidad de boletos que desea comprar el comprador
		return cantidad_boletos;
	}
	
	public void setCantidad_boletos(int cantidad_boletos) {//Establecer la cantidad de boletos que desea comprar el comprador
		this.cantidad_boletos = cantidad_boletos;
	}

	public void compraEntradas(int cantidad) {
		cantidad_boletos -= cantidad;
		//Este método restará la cantidad de boletos comprados a la cantidad de boletos que el comprador quería comprar
	}

	public int getPresupuesto() {//Obtener el presupuesto del comprador
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {//Establecer el presupuesto del comprador
		this.presupuesto = presupuesto;
	}

	public String getNombre() {//Obtener el nombre del comprador
		return nombre;
	}
	
	public void setNombre(String nombre) {//Establecer el nombre del comprador
		this.nombre = nombre;
	}

	public void setDPI(long DPI) {//Establecer el DPI del comprador
		this.DPI = DPI;
	}
}