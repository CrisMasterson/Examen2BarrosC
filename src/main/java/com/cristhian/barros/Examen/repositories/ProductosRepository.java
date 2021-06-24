package com.cristhian.barros.Examen.repositories;
import com.cristhian.barros.Examen.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos,Integer> {
}
