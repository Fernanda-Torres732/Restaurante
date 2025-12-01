package Restaurante.Fer.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import Restaurante.Fer.Entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	 List<Cliente> findByEstatus(int estatus);
	 
	 Optional<Cliente> findByCorreoCliente(String correo);
	 Cliente findByNombreCliente(String nombreCliente);
}
