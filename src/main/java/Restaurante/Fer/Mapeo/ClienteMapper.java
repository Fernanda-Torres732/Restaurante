package Restaurante.Fer.Mapeo;

import Restaurante.Fer.DTO.ClienteDto;
import Restaurante.Fer.Entidades.Cliente;

public class ClienteMapper {

	public static ClienteDto mapToClienteDTO(Cliente cliente) {
		return new ClienteDto(
		        cliente.getId(),
		        cliente.getNombreCliente(),
		        cliente.getCorreoCliente(),    
		        cliente.getTelefonoCliente(),   
		        cliente.getEstatus(),
		        cliente.getPassword(),
		        cliente.getRol()
		    );
	}

	public static Cliente mapToCliente(ClienteDto clientedto) {
		return new Cliente(clientedto.getId(), clientedto.getNombreCliente(), clientedto.getCorreoCliente(),
				clientedto.getTelefonoCliente(),  clientedto.getEstatus(), clientedto.getPassword(),
				clientedto.getRol());
	}
}
