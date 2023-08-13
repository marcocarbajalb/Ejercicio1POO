/*Marco Antonio Carbajal Bonilla (23025)
Guatemala, Universidad del Valle de Guatemala
Programación Orientada a Objetos; Sección 60
Ejercicio 1: Overloading y relaciones entre clases
Creación: 08 de agosto del 2023
Última modificación: 13 de agosto del 2023*/

package tomorrowland;
import java.util.Scanner;

class Programa {//Dentro de este programa se hará la gestión de venta de boletos de Tomorrowlan

	public static void main(String[] args) {
		
		//Crear el scan que recibirá la decision del usuario
		Scanner scan = new Scanner(System.in);
		
		//Crear la clase del comprador
		Comprador comprador = new Comprador();
		
		//Crear la clase para cada una de las localidades con sus datos respectivos
		Localidad localidad1 = new Localidad(20,1,400);
		Localidad localidad5 = new Localidad(20,5,695);
		Localidad localidad10 = new Localidad(20,10,2350);
		
		//Iniciar ciclo en el que correrá el programa
		boolean continuar = true;
		while (continuar == true) {
			//Menú que se le mostrará al usuario
			System.out.println("\n\nBIENVENIDO AL SISTEMA DE TOMORROWLAND");
			System.out.println("\nIngrese el numero que corresponda a lo que desea realizar:\n1. Nuevo comprador.\n2. Nueva solicitud de boletos.\n3. Consultar disponibilidad total.\n4. Consultar disponibilidad individual.\n5. Reporte de caja.\n6. Salir del programa.");
			int decision = scan.nextInt(); //Esta variable contendrá la decisión que haya elegido el usuario del menú
						
			if (decision==1) {//Nuevo comprador
				Scanner scan2 = new Scanner(System.in);
				
				System.out.println("\nREGISTRO DE DATOS PARA COMPRADOR ACTIVO. \n\nIngrese su nombre: ");
				String nombre = scan2.nextLine();
				comprador.setNombre(nombre);//Asignarle al comprador activo el nombre dado por el usuario
			
				System.out.println("\nIngrese su DPI: ");
				long DPI = scan2.nextLong();
				comprador.setDPI(DPI);//Asignarle al comprador activo el DPI dado por el usuario
				
				System.out.println("\nIngrese la cantidad de boletos que desea comprar: ");
				int cantidad_boletos = scan2.nextInt();
				comprador.setCantidad_boletos(cantidad_boletos);//Asignarle al comprador activo la cantidad de boletos dada por el usuario
				
				System.out.println("\nIngrese su presupuesto maximo para comprar entradas (en $): ");
				int presupuesto = scan2.nextInt();
				comprador.setPresupuesto(presupuesto);//Asignarle al comprador activo el presupuesto dado por el usuario
			}
			
			else if (decision==2) {//Nueva solicitud de boletos
				Ticket ticket = new Ticket();
				if (comprador.getNombre()!=null) {//Primero se debe verificar que haya un comprador activo
					int num_ticket = ticket.generadorAleatorio(1,33000);//Generar el ticket
					int num1 = ticket.generadorAleatorio(1,15000);//Generar el primer numero aleatorio
					int num2 = ticket.generadorAleatorio(1,15000);//Generar el segundo numero aleatorio
					
					if(comprador.getCantidad_boletos()!=0) {//Se debe verificar que el comprador todavía no haya comprado todos los boletos que deseaba comprar inicialmente
						
					if(ticket.validarPar(num1, num2, num_ticket)==true) {//Esto se cumplirá cuando la suma del ticket y los numeros aleatorios sea par
						int cantidad_compra = comprador.getCantidad_boletos();//Esta variable almacenará la cantidad de boletos que desea comprar el usuario
						int presupuesto = comprador.getPresupuesto();//Esta variable almacenará el presupuesto del comprador activo
						
						int localidad = ticket.generadorAleatorio(1,3);//Esto generará un número aleatorio entre 1 y 3
						
						if(localidad==1) {//Si el número aleatorio es 1, se elegirá la localidad 1
							int precio_localidad1 = localidad1.getPrecio_localidad();//En esta variable se almacenará el precio de la localidad
							int cantidad_venta = localidad1.getCantidad_entradas();//Esta variable almacenará la cantidad de boletos disponibles en la entrada
							if(localidad1.validarVenta(presupuesto)==true) {//Esto solo se cumplirá si se cumple la validación del espacio y del precio
								
								if(cantidad_compra<=cantidad_venta) {//Si el comprador quiere una cantidad igual o menor a los boletos disponibles
									int precio = (cantidad_compra*precio_localidad1);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quiere comprar el comprador
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que desea
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_compra);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad1.ventaEntradas(cantidad_compra);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_compra + " boletos de la localidad 1 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que desea
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad1);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad1);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad1.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 1 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
								
								else {//Si el comprador quiere más boletos de los que hay disponibles
									int precio = (cantidad_venta*precio_localidad1);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quedan disponibles
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que quedan
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_venta);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad1.ventaEntradas(cantidad_venta);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_venta + " boletos de la localidad 1 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que quedan
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad1);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad1);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad1.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 1 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
							else {//Lo que sucede si el ticket fue aprobado y se eligio la localidad, pero no se cumple la validación del espacio y del precio
								System.out.println("\nTRANSACCION FALLIDA. \nSe aprobo el ticket y se definio la localidad 1, pero no hay espacio disponible y/o el precio de las entradas excede el presupuesto del comprador (" + comprador.getNombre()+ ").");}}
						
						else if(localidad==2) {//Si el número aleatorio es 2, se elegirá la localidad 5
							int precio_localidad5 = localidad5.getPrecio_localidad();//En esta variable se almacenará el precio de la localidad
							int cantidad_venta = localidad5.getCantidad_entradas();//Esta variable almacenará la cantidad de boletos disponibles en la entrada
							if(localidad5.validarVenta(presupuesto)==true) {//Esto solo se cumplirá si se cumple la validación del espacio y del precio
								
								if(cantidad_compra<=cantidad_venta) {//Si el comprador quiere una cantidad igual o menor a los boletos disponibles
									int precio = (cantidad_compra*precio_localidad5);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quiere comprar el comprador
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que desea
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_compra);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad5.ventaEntradas(cantidad_compra);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_compra + " boletos de la localidad 5 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que desea
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad5);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad5);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad5.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 5 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
								
								else {//Si el comprador quiere más boletos de los que hay disponibles
									int precio = (cantidad_venta*precio_localidad5);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quedan disponibles
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que quedan
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_venta);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad5.ventaEntradas(cantidad_venta);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_venta + " boletos de la localidad 5 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que quedan
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad5);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad5);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad5.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 5 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
							else {//Lo que sucede si el ticket fue aprobado y se eligio la localidad, pero no se cumple la validación del espacio y del precio
								System.out.println("\nTRANSACCION FALLIDA. \nSe aprobo el ticket y se definio la localidad 5, pero no hay espacio disponible y/o el precio de las entradas excede el presupuesto del comprador (" + comprador.getNombre()+ ").");}}
						
						else if(localidad==3) {//Si el número aleatorio es 3, se elegirá la localidad 10
							int precio_localidad10 = localidad10.getPrecio_localidad();//En esta variable se almacenará el precio de la localidad
							int cantidad_venta = localidad5.getCantidad_entradas();//Esta variable almacenará la cantidad de boletos disponibles en la entrada
							if(localidad10.validarVenta(presupuesto)==true) {//Esto solo se cumplirá si se cumple la validación del espacio y del precio
								
								if(cantidad_compra<=cantidad_venta) {//Si el comprador quiere una cantidad igual o menor a los boletos disponibles
									int precio = (cantidad_compra*precio_localidad10);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quiere comprar el comprador
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que desea
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_compra);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad10.ventaEntradas(cantidad_compra);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_compra + " boletos de la localidad 10 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que desea
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad10);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad10);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad10.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 10 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
								
								else {//Si el comprador quiere más boletos de los que hay disponibles
									int precio = (cantidad_venta*precio_localidad10);//El precio se calcula multiplicando el precio de los boletos por la cantidad de boletos que quedan disponibles
									
									if(precio<=presupuesto) {//Si al comprador le alcanza para todos los boletos que quedan
										comprador.setPresupuesto(presupuesto - precio);
										comprador.compraEntradas(cantidad_venta);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad10.ventaEntradas(cantidad_venta);
										//Se resta el precio al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + cantidad_venta + " boletos de la localidad 10 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + precio + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-precio) + ".");}
										//Se muestra el mensaje de exito con la informacion de la transaccion
									
									else {//Si al comprador no le alcanza para todos los boletos que quedan
										int num_boletos = Math.floorDiv(presupuesto,precio_localidad10);//La cantidad de boletos que le alcanza para comprar será el entero inferior más cercano (floor) a la division entre el presupuesto del comprador y el precio de la localidad
										int costo = (num_boletos*precio_localidad10);//El costo se calcula multiplicando el precio de los boletos por la cantidad de boletos que le alcanza para comprar al comprador
										comprador.setPresupuesto(presupuesto - costo);
										comprador.compraEntradas(num_boletos);//Restar los boletos vendidos a la cantidad que quería comprar el comprador
										localidad10.ventaEntradas(num_boletos);
										//Se resta el costo al presupuesto para encontrar el nuevo presupuesto (que se establece para el comprador activo), y a los boletos de la localidad se le resta la cantidad de boletos comprada
										System.out.println("\nTRANSACCION EXITOSA. \nSe han vendido " + num_boletos + " boletos de la localidad 10 a " + comprador.getNombre() + ".\nEl total de la compra ha sido de $" + costo + ".\nEl presupuesto restante del comprador es de $" + (presupuesto-costo) + ".");}}}
										//Se muestra el mensaje de exito con la informacion de la transaccion
							else {//Lo que sucede si el ticket fue aprobado y se eligio la localidad, pero no se cumple la validación del espacio y del precio
								System.out.println("\nTRANSACCION FALLIDA. \nSe aprobo el ticket y se definio la localidad 10, pero no hay espacio disponible y/o el precio de las entradas excede el presupuesto del comprador (" + comprador.getNombre()+ ").");}}}
					
					else {//Lo que sucederá si la suma del ticket y los dos números aleatorios no es par
						System.out.println("\nRECHAZADO.\nEl ticket del comprador no fue aprobado para comprar boletos.");}}
					
					else {//Lo que sucederá si la cantidad de boletos que desea el comprador ya llegó a 0
						System.out.println("\nCOMPRADOR SATISFECHO. \nEl comprador ya ha adquirido todos los boletos que solicito inicialmente, por lo que no se pueden comprar mas boletos.");}}
				
				else {//Lo que sucederá si no hay un comprador activo al elegir esta opción en el menú
					System.out.println("\n***ERROR***\n Debe haber un comprador activo para acceder a esta funcion.");}}
			
			else if (decision==3) {//Consultar disponibilidad total
				int ventas1 = localidad1.boletosVendidos(); //Esta variable contendrá el total de boletos vendidos en la localidad 1
				int ventas5 = localidad5.boletosVendidos(); //Esta variable contendrá el total de boletos vendidos en la localidad 5
				int ventas10 = localidad10.boletosVendidos(); //Esta variable contendrá el total de boletos vendidos en la localidad 10
				int totalventas = ventas1 + ventas5 + ventas10; //La suma de los boletos vendidos en todas las localidades
			
				int disponible1 = localidad1.getCantidad_entradas(); //Esta variable contendrá la cantidad de boletos que quedan en la localidad 1
				int disponible5 = localidad5.getCantidad_entradas(); //Esta variable contendrá la cantidad de boletos que quedan en la localidad 5
				int disponible10 = localidad10.getCantidad_entradas(); //Esta variable contendrá la cantidad de boletos que quedan en la localidad 10
				int disponibilidadtotal = disponible1 + disponible5 + disponible10; //La suma de los boletos que quedan en todas las localidades
			
				System.out.println("\nLOCALIDAD 1: \nSe han vendido " + ventas1 + " boletos.\nQuedan " + disponible1 + " boletos.");
				System.out.println("\nLOCALIDAD 5: \nSe han vendido " + ventas5 + " boletos.\nQuedan " + disponible5 + " boletos.");
				System.out.println("\nLOCALIDAD 10: \nSe han vendido " + ventas10 + " boletos.\nQuedan " + disponible10 + " boletos.");
				System.out.println("\nTOTALES: \nSe han vendido " + totalventas + " boletos en total.\nQuedan " + disponibilidadtotal + " boletos disponibles entre todas las localidades.");}
			
			else if (decision==4) {//Consultar disponibilidad individual
				Scanner scan3 = new Scanner(System.in);
				
				System.out.println("\nIngrese el numero que corresponde a la localidad que desea consultar:\n1. Localidad 1\n2. Localidad 5\n3. Localidad 10");
				int localidad = scan3.nextInt(); //Esta variable contendrá la localidad que haya elegido el usuario para visualizar la disponibilidad
				
				switch(localidad){
				case 1:{ //Si el usuario elige la Localidad 1
					int boletos_disponibles = localidad1.getCantidad_entradas(); //Esta variable contendrá la cantidad de entradas disponibles en la localidad
					System.out.println("\nLOCALIDAD 1. \nPara la localidad 1, hay un total de " + boletos_disponibles + " boletos disponibles.");
					break;}
				case 2:{ //Si el usuario elige la Localidad 5
					int boletos_disponibles = localidad5.getCantidad_entradas(); //Esta variable contendrá la cantidad de entradas disponibles en la localidad
					System.out.println("\nLOCALIDAD 5. \nPara la localidad 5, hay un total de " + boletos_disponibles + " boletos disponibles.");
					break;}
				case 3:{ //Si el usuario elige la Localidad 10
					int boletos_disponibles = localidad10.getCantidad_entradas(); //Esta variable contendrá la cantidad de entradas disponibles en la localidad
					System.out.println("\nLOCALIDAD 10. \nPara la localidad 10, hay un total de " + boletos_disponibles + " boletos disponibles.");
					break;}
				default:{ //Si el ususario no elige ninguna de las opciones disponibles
					System.out.println("\n***ERROR***\nEl numero ingresado no corresponde a ninguna localidad.");}}
				
			}
			
			else if (decision==5) {//Reporte de caja
				int total_caja = localidad1.reporteCaja() + localidad5.reporteCaja() + localidad10.reporteCaja(); //Esta variable contendrá la suma del dinero generado en cada localidad
				System.out.println("\nREPORTE DE CAJA. \nEn total, los boletos vendidos en las tres localidades han generado $" + total_caja + ".");
			}
			
			else if (decision==6) {//Salir del programa
				System.out.println("\nHa salido del programa exitosamente.");
				continuar=false;
				scan.close();
			}
			
			else {//Programación defensiva
				System.out.println("\n***ERROR***\nLa decision ingresada no es valida.");
			}
		}
	}
}