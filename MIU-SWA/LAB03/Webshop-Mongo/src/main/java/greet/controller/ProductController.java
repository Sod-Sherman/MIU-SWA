package greet.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import greet.model.Product;
import greet.service.ProductCatalogService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductCatalogService productCatalogService;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productCatalogService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{prodId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer prodId) {
		return new ResponseEntity<>(productCatalogService.getProduct(prodId), HttpStatus.OK);
	}

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@RequestBody Product product) {

		product = productCatalogService.addProduct(product);

		return new ResponseEntity<>(productCatalogService.getProduct(product.getProductId()), HttpStatus.OK);
	}

	@GetMapping(value = "/addstock/{prodId}/{quantity}/{locationcode}")
	public ResponseEntity<Product> setStock(
			@PathVariable("prodId") Integer prodId, 
			@PathVariable("quantity") int quantity,
			@PathVariable("locationcode") String locationcode) {
		productCatalogService.setStock(prodId, quantity, locationcode);
		return new ResponseEntity<>(productCatalogService.getProduct(prodId), HttpStatus.OK);
	}

}
