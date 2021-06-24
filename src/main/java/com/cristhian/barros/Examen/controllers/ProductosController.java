package com.cristhian.barros.Examen.controllers;

import com.cristhian.barros.Examen.entities.Productos;
import com.cristhian.barros.Examen.entities.Productos;
import com.cristhian.barros.Examen.repositories.ProductosRepository;
import com.cristhian.barros.Examen.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductosController {

    private ProductosRepository productosRepository;

    @Autowired
    public ProductosController(ProductosRepository productRepository) {
        this.productosRepository = productRepository;
    }

    public List<Productos> findAllProduct() {
        return this.productosRepository.findAll();
    }

    public Optional<Productos> findProductById(int id){
        return this.productosRepository.findById(id);
    }


    public void createProduct(Productos productos){
        this.productosRepository.save(productos);
    }

    public boolean editProductById(int id, Productos productos){
        Optional<Productos> productOptional = this.findProductById(id);
        if( !productOptional.isPresent()) return false;
        Productos productosdb = productOptional.get();
        productosdb.setMarca(productos.getMarca());
        productosdb.setModelo(productos.getModelo());
        productosdb.setPrice(productos.getPrice());
        productosRepository.save(productosdb);
        return true;
    }

    public boolean deleteProductById(int id) {
        Optional<Productos> productOptional = this.findProductById(id);
        if (!productOptional.isPresent()) return false;
        productosRepository.deleteById(id);
        return true;
    }



}
