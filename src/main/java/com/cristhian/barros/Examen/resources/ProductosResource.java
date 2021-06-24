package com.cristhian.barros.Examen.resources;

import com.cristhian.barros.Examen.controllers.ProductosController;
import com.cristhian.barros.Examen.entities.Productos;
import com.cristhian.barros.Examen.resources.exceptions.EditProductException;
import com.cristhian.barros.Examen.resources.exceptions.ProductCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ProductosResource.PRODUCTOS)
public class ProductosResource {
    public static final String PRODUCTOS = "/producto";
    public static final String ID = "/{id}";


    private ProductosController productosController;

    @Autowired
    public ProductosResource(ProductosController productosController) {
        this.productosController = productosController;
    }

    @GetMapping
    public List<Productos> getAllProduct(@RequestParam(required = false) String ci) {
        if (ci == null) return this.productosController.findAllProduct();
        return this.productosController.findAllProduct();

    }


    @GetMapping(value = ID)
    public ResponseEntity getProductById(@PathVariable int id) {
        Optional<Productos> productOptional = this.productosController.findProductById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity(productOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity("\"El producto no  existe\"", HttpStatus.NOT_FOUND);
        }

    }



    @PostMapping
    public ResponseEntity createProduct(@RequestBody Productos productos) throws ProductCreateException {
        try {
            this.productosController.createProduct(productos);
            return new ResponseEntity("\"El producto fue creado\"", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ProductCreateException("los datos enviados no son los correctos");
        }

    }

    @PutMapping(value = ID)
    public ResponseEntity editProductById(@RequestBody Productos product, @PathVariable int id) throws EditProductException {
        try {
            if (this.productosController.editProductById(id, product))
                return new ResponseEntity("\"El producto fue edito\"", HttpStatus.ACCEPTED);
            return new ResponseEntity("\"El producto no  existe\"", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new EditProductException("los datos enviados no son los correctos");
        }
    }

    @DeleteMapping(value = ID)
    public  ResponseEntity deleteProduct(@PathVariable int id)
        {
                if (this.productosController.deleteProductById(id))
                    return new ResponseEntity("\"El producto fue eliminado\"", HttpStatus.ACCEPTED);
                return new ResponseEntity("\"El producto no  existe\"", HttpStatus.NOT_FOUND);


    }

}
