package Restaurante.Fer.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import Restaurante.Fer.DTO.ClienteDto;
import Restaurante.Fer.Entidades.Cliente;
import Restaurante.Fer.Mapeo.ClienteMapper;
import Restaurante.Fer.Repository.ClienteRepository;
import Restaurante.Fer.service.ClienteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{
	private ClienteRepository clienteRepository;
	
	@Override
	public ClienteDto createCliente (ClienteDto clienteDto) {
		
		Cliente cliente = ClienteMapper.mapToCliente(clienteDto);
		cliente.setEstatus(1);
		Cliente savedCliente = clienteRepository.save(cliente);
		return ClienteMapper.mapToClienteDTO(savedCliente);
		
	}
	@Override
    public ClienteDto findByNombreCliente(String nombreCliente) {
        Cliente cliente = clienteRepository.findByNombreCliente(nombreCliente);

        if (cliente == null) {
            return null;
        }

        return mapToDto(cliente);
    }
	
	@Override
	public ClienteDto getClienteById(Integer Id) {
		Cliente cliente = clienteRepository.findById(Id).orElse(null);
		return ClienteMapper.mapToClienteDTO(cliente);
	}
	@Override
	public List<ClienteDto> getAllCliente(){
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes.stream().map((cliente) -> ClienteMapper.mapToClienteDTO(cliente)).collect(Collectors.toList());
				
	}
	@Override
	public ClienteDto updateCliente(Integer Id, ClienteDto updateCliente) {
		Cliente cliente = clienteRepository.findById(Id).orElse(null);
		cliente.setNombreCliente(updateCliente.getNombreCliente());
		cliente.setTelefonoCliente(updateCliente.getTelefonoCliente());
		cliente.setCorreoCliente(updateCliente.getCorreoCliente());
		Cliente updateClienteObj = clienteRepository.save(cliente);

		return ClienteMapper.mapToClienteDTO(updateClienteObj);
	}
	public void desactivarCliente(int idCliente) {
	    Cliente cliente = clienteRepository.findById(idCliente)
	            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
	    cliente.setEstatus(0);
	    clienteRepository.save(cliente);
	}
	 @Override
	    public List<ClienteDto> getAllClientesActivos() {
	        return clienteRepository.findByEstatus(1)
	                .stream()
	                .map(this::mapToDto) 
	                .collect(Collectors.toList());
	    }
	 private ClienteDto mapToDto(Cliente cliente) {
	        return new ClienteDto(
	        		cliente.getId(),
	        		cliente.getNombreCliente(),
	        		cliente.getTelefonoCliente(),
	        		cliente.getCorreoCliente(),
	        		cliente.getEstatus(),
	        		cliente.getPassword(),
	        		cliente.getRol()
	        );
	    }
}
