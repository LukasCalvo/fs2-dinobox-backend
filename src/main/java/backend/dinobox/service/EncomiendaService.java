package backend.dinobox.service;

import backend.dinobox.enums.EstadoEncomienda;
import backend.dinobox.model.Encomienda;
import backend.dinobox.repository.EncomiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EncomiendaService {

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;

    public Encomienda crearEncomienda(Encomienda encomienda) {
        String codigo = generarCodigoAleatorio();
        encomienda.setCodigoSeguimiento(codigo);

        // si no mandan estado, parte en recepcion
        if (encomienda.getEstado() == null) {
            encomienda.setEstado(EstadoEncomienda.EN_RECEPCION);
        }

        return encomiendaRepository.save(encomienda);
    }

    public Optional<Encomienda> consultarPorCodigo(String codigo) {
        return encomiendaRepository.findByCodigoSeguimiento(codigo);
    }

    // actualiza los datos que vengan y el estado
    public Encomienda actualizar(Long id, Encomienda datos) {
        Optional<Encomienda> encomiendaOpt = encomiendaRepository.findById(id);
        if (encomiendaOpt.isEmpty()) {
            throw new RuntimeException("No se ha encontrado una encomienda con el id: " + id);
        }

        Encomienda encomienda = encomiendaOpt.get();
        if (datos.getRemitente() != null) {
            encomienda.setRemitente(datos.getRemitente());
        }
        if (datos.getDestinatario() != null) {
            encomienda.setDestinatario(datos.getDestinatario());
        }
        if (datos.getDireccionDestino() != null) {
            encomienda.setDireccionDestino(datos.getDireccionDestino());
        }
        if (datos.getEstado() != null) {
            encomienda.setEstado(datos.getEstado());
        }
        return encomiendaRepository.save(encomienda);
    }

    public void eliminar(Long id) {
        if (!encomiendaRepository.existsById(id)) {
            throw new RuntimeException("No se ha encontrado una encomienda con el id: " + id);
        }
        encomiendaRepository.deleteById(id);
    }

    public List<Encomienda> listarTodas() {
        return encomiendaRepository.findAll();
    }

    private String generarCodigoAleatorio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}