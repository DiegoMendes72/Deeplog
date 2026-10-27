package br.com.deeplog;

import br.com.deeplog.model.*;
import br.com.deeplog.excecoes.ValidacaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ModelagemTest {

    @Test
    void deveCriarRegistroDeMergulho() {
        Mergulhador mergulhador = new Mergulhador("Diego", "diego@email.com", "hash");
        PontoMergulho ponto = new PontoMergulho("Laje de Santos", "SP", "Bonito", "Correnteza");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Avançado", 30.0);
        
        RegistroMergulho registro = new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, 22.0);
        
        assertEquals(mergulhador, registro.getAutor());
        assertEquals(45, registro.getDuracaoMin());
        assertEquals(22.0, registro.getProfundidadeMaximaM());
    }

    @Test
    void naoDevePermitirProfundidadeNegativa() {
        Mergulhador mergulhador = new Mergulhador("Diego", "diego@email.com", "hash");
        PontoMergulho ponto = new PontoMergulho("Laje", "SP", "X", "Y");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Avançado", 30.0);

        assertThrows(ValidacaoException.class, () -> {
            new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, -5.0);
        });
    }

    @Test
    void deveAdicionarFotografiaAoRegistro() {
        Mergulhador mergulhador = new Mergulhador("Diego", "diego@email.com", "hash");
        PontoMergulho ponto = new PontoMergulho("Laje", "SP", "X", "Y");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Avançado", 30.0);
        RegistroMergulho registro = new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, 20.0);

        Fotografia foto = new Fotografia("foto.jpg", "Legenda");
        boolean adicionou = registro.adicionarFoto(foto);

        assertTrue(adicionou);
        assertEquals(1, registro.getFotografias().size());
    }
}
