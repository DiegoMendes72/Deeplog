package br.com.deeplog;

import br.com.deeplog.model.*;
import br.com.deeplog.excecoes.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ModelagemTest {

    private Mergulhador mergulhador;
    private PontoMergulho ponto;
    private PerfilMergulho perfil;

    @BeforeEach
    void setUp() {
        mergulhador = new Mergulhador("Lucas", "lucas@email.com", "hash");
        ponto = new PontoMergulho("Laje de Santos", "SP", "Bonito", "Correnteza");
        perfil = new PerfilMergulho(ponto, "Avançado", 30.0);
    }

    @Test
    void deveCriarRegistroDeMergulho() {
        RegistroMergulho registro = new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, 22.0);
        
        assertEquals(mergulhador, registro.getAutor());
        assertEquals(45, registro.getDuracaoMin());
        assertEquals(22.0, registro.getProfundidadeMaximaM());
    }

    @Test
    void naoDevePermitirProfundidadeNegativa() {
        assertThrows(ValidacaoException.class, () -> {
            new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, -5.0);
        });
    }

    @Test
    void deveAdicionarFotografiaAoRegistro() {
        RegistroMergulho registro = new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45, 20.0);

        Fotografia foto = new Fotografia("foto.jpg", "Legenda");
        boolean adicionou = registro.adicionarFoto(foto);

        assertTrue(adicionou);
        assertEquals(1, registro.getFotografias().size());
    }
}
