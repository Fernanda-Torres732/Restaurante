package Restaurante.Fer.service;
import java.util.List;

import Restaurante.Fer.DTO.*;
public interface ClienteService {
ClienteDto createCliente (ClienteDto clienteDto);
	ClienteDto getClienteById(Integer Id);
	List<ClienteDto> getAllCliente();
	ClienteDto updateCliente(Integer Id, ClienteDto updateCliente);
	void desactivarCliente(int id);
	List<ClienteDto> getAllClientesActivos();
	
	ClienteDto findByNombreCliente(String nombreCliente);
}
