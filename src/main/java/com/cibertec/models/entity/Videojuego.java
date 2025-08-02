package com.cibertec.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_videojuego")
@NamedQuery(name="Videojuego.findAll", query="SELECT e FROM Videojuego e")
public class Videojuego implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name = "codigo", length = 25)
		@NotEmpty
		private String codigo;
		
		@Column(name = "nombre", length = 100)
		@NotEmpty
		private String nombre;
		
		@Column(name = "consola", length = 100)
		@NotEmpty
		private String consola;
		
		@Column(name = "genero", length = 100)
		@NotNull
		private String genero;
		
		@Column(name = "descripcion", length = 300)
		@NotNull
		private String descripcion;
		
		@Column(name = "stock")
		@NotNull
		private int stock;
		
		@Column(name = "precio")
		@NotNull
		private BigDecimal precio;

		@Column(name = "activo")
		private Boolean activo = true;

//		@Temporal(TemporalType.TIMESTAMP)
//		private Date createdAt;
//		@Temporal(TemporalType.TIMESTAMP)
//		private Date updatedAt;
		
//		@ManyToOne
//		@JoinColumn(name="tienda_id")
//		private Tienda tienda;
		
		public Videojuego() {
			
		}
		
		public Videojuego(String codigo, String nombre, String consola, String genero, String descripcion, int stock, BigDecimal precio) {
			super();
			this.codigo = codigo;
			this.nombre = nombre;
			this.consola = consola;
			this.genero = genero;
			this.descripcion = descripcion;
			this.stock = stock;
			this.precio = precio;
		}

		public Videojuego(int id, String codigo, String nombre, String consola, String genero, String descripcion, int stock, BigDecimal precio) {
			super();
			this.id = id;
			this.codigo = codigo;
			this.nombre = nombre;
			this.consola = consola;
			this.genero = genero;
			this.descripcion = descripcion;
			this.stock = stock;
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getConsola() {
			return consola;
		}

		public void setConsola(String consola) {
			this.consola = consola;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public BigDecimal getPrecio() {
			return precio;
		}

		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}

		public Boolean getActivo() {
			return activo;
		}

		public void setActivo(Boolean activo) {
			this.activo = activo;
		}

		@Override
		public String toString() {
			return "Videojuego [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", consola=" + consola
					+ ", genero=" + genero + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
					+ "]";
		}
			
}
