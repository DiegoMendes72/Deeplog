package br.com.deeplog;

import br.com.deeplog.gamificacao.RegraMergulhoProfundo;
import br.com.deeplog.gamificacao.RegraPrimeiroRegistro;
import br.com.deeplog.model.*;
import br.com.deeplog.requisitos.EstadoVerificacao;
import br.com.deeplog.requisitos.RequisitoExperiencia;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PadroesTest {

    @Test
    void regrasDeConquistaDevemFuncionar() {
        Mergulhador mergulhador = new Mergulhador("Lucas", "lucas@email.com", "hash");
        PerfilMergulho perfil = new PerfilMergulho(new PontoMergulho("Laje", "SP", "Bonito", "Fraca"), "Avançado", 30.0);
        List<RegistroMergulho> registros = new ArrayList<>();

        RegraPrimeiroRegistro primeiroRegistro = new RegraPrimeiroRegistro(registros);
        RegraMergulhoProfundo profundo = new RegraMergulhoProfundo(registros, 20);
        assertFalse(primeiroRegistro.atendida(mergulhador));

        registros.add(new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 40, 10.0));
        assertTrue(primeiroRegistro.atendida(mergulhador));
        assertFalse(profundo.atendida(mergulhador));
    }

    @Test
    void requisitoSemMergulhadorDeveSerIndeterminado() {
        RequisitoExperiencia requisito = new RequisitoExperiencia("Mínimo de 1 mergulho", "Manual", 1);
        assertEquals(EstadoVerificacao.INDETERMINADO, requisito.verificar(null).estado());
    }
}
