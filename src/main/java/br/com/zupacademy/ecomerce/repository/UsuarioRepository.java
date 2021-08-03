package br.com.zupacademy.ecomerce.repository;

import br.com.zupacademy.ecomerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String email);
}
