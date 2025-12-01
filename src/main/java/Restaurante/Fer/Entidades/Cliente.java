package Restaurante.Fer.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "nombreCliente")
	private String nombreCliente;

	@Column(name = "telefonoCliente")
	private String telefonoCliente;

	@Column(name = "correoCliente")
	private String correoCliente;
	
	@Column(name = "estatus")
    private int estatus;
	
	@Column(name = "password") 
	private String password;

	@Column(name = "rol") 
	    private String rol;
	
}