/*Marco Antonio Carbajal Bonilla (23025)
Guatemala, Universidad del Valle de Guatemala
Programación Orientada a Objetos; Sección 60
Ejercicio 1: Overloading y relaciones entre clases
Creación: 12 de agosto del 2023
Última modificación: 13 de agosto del 2023*/

package tomorrowland;
import java.util.Random;

class Ticket {//Esta clase es la que creará los números aleatorios para generar el ticket y validarlo
	
	public Ticket() {//Constructor

	}
	
	public int generadorAleatorio(int minimo, int maximo) {
		int aleatorio;
		Random xrandom = new Random();
		aleatorio = xrandom.nextInt(maximo-minimo+1)+minimo;
		return aleatorio;
		//Este método generará un número aleatorio que se encuentre entre los valores que se le den como parámetros
	}
	
	public boolean validarPar(int num1, int num2, int num_ticket) {
		boolean valor;
		int num = num1 + num2 + num_ticket;
		if (num%2==0) {
			valor = true;
		}
		else {
			valor = false;
		}
		return valor;
		//Este método validará que la suma de los tres números dados como parámetros sea par (caso en el que regresará un true), de lo contrario regresará un false
	}
}