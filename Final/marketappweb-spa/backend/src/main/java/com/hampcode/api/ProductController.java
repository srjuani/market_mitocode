package com.hampcode.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.exception.ModeloNotFoundException;
import com.hampcode.model.domain.Product;
import com.hampcode.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = new ArrayList<>();

		try {
			products = productService.getAll();
		} catch (Exception e) {

		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Optional<Product> product;
		try {
			product = productService.getOne(id);
			if (!product.isPresent()) {
				throw new ModeloNotFoundException("ID: " + id);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
		Product productNew = new Product();

		try {
			productNew = productService.saveOrUpdate(product);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<Product>(productNew, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
		try {
			productService.saveOrUpdate(product);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		Optional<Product> product;

		try {
			product = productService.getOne(id);
			if (!product.isPresent()) {
				throw new ModeloNotFoundException("ID: " + id);
			} else {
				productService.deleteById(id);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Product>(HttpStatus.OK);

	}
}
