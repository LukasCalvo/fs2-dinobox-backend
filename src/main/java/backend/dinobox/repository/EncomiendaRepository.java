package backend.dinobox.repository;

import backend.dinobox.model.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {
    Optional<Encomienda> findByCodigoSeguimiento(String codigoSeguimiento);
}