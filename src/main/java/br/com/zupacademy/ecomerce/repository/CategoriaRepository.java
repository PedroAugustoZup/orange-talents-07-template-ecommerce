package br.com.zupacademy.ecomerce.repository;

import br.com.zupacademy.ecomerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
