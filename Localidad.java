/*Marco Antonio Carbajal Bonilla (23025)
Guatemala, Universidad del Valle de Guatemala
Programación Orientada a Objetos; Sección 60
Ejercicio 1: Overloading y relaciones entre clases
Creación: 08 de agosto del 2023
Última modificación: 13 de agosto del 2023*/

package tomorrowland;

class Localidad {//Esta clase contendrá la información de las localidades
	//Estas son las variables que almacnarán la información de la localidad
	private int cantidad_entradas, num_localidad, precio_localidad;
	
	//Esta variable no cambiará a medida que se venden las entradas, para tener una referencia de cuántas entradas había inicialmente
	private int cantidad_entradas_iniciales;
	
	public Localidad(int cantidad_entradas, int num_localidad, int precio_localidad) {//Constructor
		this.cantidad_entradas = cantidad_entradas;
		this.cantidad_entradas_iniciales = cantidad_entradas;
		this.num_localidad = num_localidad;
		this.precio_localidad = precio_localidad;
	}
	
	public boolean validarVenta(int presupuesto) {
		boolean valor1 = false;
		boolean valor2 = false;
		boolean valor3 = false;
		if (cantidad_entradas > 0) {
			valor1 = true;}
		if (precio_localidad <= presupuesto) {
			valor2 = true;}
		if (valor1==true && valor2==true) {
			valor3=true;}
		return valor3;
		//Este método comprobará que haya más de 0 entradas disponibles en la localidad y que el presupuesto dado como parámetro sea mayor o igual al precio de una entrada en la localidad (regresando true cuando ambos se cumplan)
		}

	public int getCantidad_entradas() {//Obtener la cantidad de entradas disponibles que hay en la localidad
		return cantidad_entradas;
	}

	public void ventaEntradas(int cantidad) {
		cantidad_entradas -= cantidad;
		//Este método restará a la cantidad de entradas disponibles en la localidad el valor que se le dé como parámetro
	}
	
	public int getPrecio_localidad() {//Obtener el precio de las entradas de la localidad
		return precio_localidad;
	}
	
	public int boletosVendidos() {
		int ventas = cantidad_entradas_iniciales - cantidad_entradas;
		return ventas;
		//Este método calculará la cantidad de boletos que se han vendido en total en la localidad
	}
	
	public int reporteCaja() {
		int total = (boletosVendidos())*precio_localidad;
		return total;
		//Este método calculará la cantidad total de dinero que han generado los boletos en la localidad
	}
}