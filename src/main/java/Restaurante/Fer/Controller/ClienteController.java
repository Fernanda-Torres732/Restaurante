package Restaurante.Fer.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Restaurante.Fer.DTO.ClienteDto;
import Restaurante.Fer.service.ClienteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	private ClienteService clienteService;
	@PostMapping
	public ResponseEntity<ClienteDto>crearCliente(@RequestBody ClienteDto clienteDto){
		ClienteDto guardarCliente = clienteService.createCliente(clienteDto);
		return new ResponseEntity<>(guardarCliente, HttpStatus.CREATED);
	}
	
	@GetMapping("{Id}")
	public ResponseEntity<ClienteDto> getClienteById(@PathVariable("Id") Integer Id) {
		ClienteDto clienteDto = clienteService.getClienteById(Id);
		return ResponseEntity.ok(clienteDto);
	}
	@GetMapping
	public ResponseEntity<List<ClienteDto>> getAllClientes(){
		List<ClienteDto> clientes = clienteService.getAllClientesActivos();
		return ResponseEntity.ok(clientes);
	}
	
	@PutMapping("/{id}/actualizar")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("id") Integer id,
                                                      @RequestBody ClienteDto updateCliente) {
		ClienteDto clienteDto = clienteService.updateCliente(id, updateCliente);
        return ResponseEntity.ok(clienteDto);
    }

	@PutMapping("/{id}/desactivar")
	public ResponseEntity<Void> desactivarCliente(@PathVariable int id) {
	    clienteService.desactivarCliente(id);
	    return ResponseEntity.noContent().build();
	}
	@CrossOrigin(origins = "http://localhost:4000")
	@PostMapping("/login")
	public ResponseEntity<?> loginCliente(@RequestBody ClienteDto loginDto) {

	    // 1. buscar cliente por nombre (o correo, cambia lo que necesites)
	    ClienteDto cliente = clienteService.findByNombreCliente(loginDto.getNombreCliente());

	    // 2. si no existe
	    if (cliente == null) {
	    	return ResponseEntity.status(401).body("Credenciales inválidas (cliente)");
	    }

	    // 3. validar contraseña
	    if (!cliente.getPassword().equals(loginDto.getPassword())) {
	    	return ResponseEntity.status(401).body("Credenciales inválidas (cliente)");
	    }

	    // 4. login exitoso: regresar datos
	    return ResponseEntity.ok(Map.of(
	        "id", cliente.getId(),
	        "nombreCliente", cliente.getNombreCliente(),
	        "rol", cliente.getRol() == null ? "CLIENTE" : cliente.getRol(),
	        "token", "fake-jwt"
	    ));
	}



}
