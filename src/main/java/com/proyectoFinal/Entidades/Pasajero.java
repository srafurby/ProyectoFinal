package com.proyectoFinal.Entidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pasajeros")
public class Pasajero {
	@Id
	private String dni;

	private String nombre;
	private String apellidos;
	private LocalDate f_nacimiento;
	private int admin;
	private String pass;

	private String email;

	//CONSTRUCTOR
	public Pasajero() {
	}

	public Pasajero(String dni, String nombre, String apellidos, LocalDate f_nacimiento, int admin, String pass,
			String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.f_nacimiento = f_nacimiento;
		this.admin = admin;
		this.pass = pass;
		this.email = email;
	}
	
	//GETTER AND SETTER
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(LocalDate f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) { //PASSWORD WITH MD5
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pass.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			this.pass = hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
