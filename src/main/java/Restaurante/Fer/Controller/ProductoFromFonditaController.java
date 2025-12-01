package Restaurante.Fer.Controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

import Restaurante.Fer.DTO.ProductoDto; 

@RestController
public class ProductoFromFonditaController {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public ProductoFromFonditaController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }

    @GetMapping("/restaurante/productos")
    public List<ProductoDto> getProductosFromFondita() {
      
        ServiceInstance serviceInstance = discoveryClient.getInstances("Fondita").get(0);

        return restClient.get()
                .uri(serviceInstance.getUri() + "/api/producto")
                .retrieve()
                .body(List.class);
    }
}
