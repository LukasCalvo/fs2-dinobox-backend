package backend.dinobox.model;

import backend.dinobox.enums.EstadoEncomienda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// tabla encomiendas
@Entity
@Table(name = "encomiendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoSeguimiento;

    @Column(nullable = false)
    private String remitente;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private String direccionDestino;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEncomienda estado;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;
}