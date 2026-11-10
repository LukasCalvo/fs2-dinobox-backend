package backend.dinobox.controller;

import backend.dinobox.model.Encomienda;
import backend.dinobox.service.EncomiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/encomiendas")
public class EncomiendaController {

    @Autowired
    private EncomiendaService encomiendaService;

    // crea la encomienda y responde con el codigo
    @PostMapping
    public ResponseEntity<Encomienda> crearEncomienda(@RequestBody Encomienda encomienda) {
        Encomienda nuevaEncomienda = encomiendaService.crearEncomienda(encomienda);
        return ResponseEntity.ok(nuevaEncomienda);
    }

    // consulta por codigo de seguimiento
    @GetMapping("/{codigo}")
    public ResponseEntity<?> consultarPorCodigo(@PathVariable String codigo) {
        Optional<Encomienda> encomienda = encomiendaService.consultarPorCodigo(codigo);
        if (encomienda.isPresent()) {
            return ResponseEntity.ok(encomienda.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // edita remitente, destinatario, direccion y estado
    @PutMapping("/{id}")
    public ResponseEntity<Encomienda> actualizar(
            @PathVariable Long id,
            @RequestBody Encomienda datos) {
        try {
            return ResponseEntity.ok(encomiendaService.actualizar(id, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // borra la encomienda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            encomiendaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // lista todas
    @GetMapping
    public ResponseEntity<List<Encomienda>> listarTodas() {
        return ResponseEntity.ok(encomiendaService.listarTodas());
    }
}