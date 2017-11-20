package com.rupestre.gmail;

public class EnviarCorreo {

	public static void main(String[] args) {

		Correo correo = new Correo("destino@gmail.com", 
				"Registro en nuestro portal 2", 
				"Bienvenido a nuestro portal de clientes 2");


		System.out.println(":::: Inicio :::::");
		if (correo.enviarCorreo()) {
			System.out.println("Se envio de manera exitosa");
		} else {
			System.out.println("Error al enviar correo");
		}
		System.out.println(":::: Fin ::::");

	}

}
