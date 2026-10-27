package br.com.deeplog;

import br.com.deeplog.autenticacao.Senhas;
import br.com.deeplog.autenticacao.ServicoAutenticacao;
import br.com.deeplog.excecoes.AutenticacaoException;
import br.com.deeplog.model.Mergulhador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutenticacaoTest {

    private ServicoAutenticacao servico;

    @BeforeEach
    void setUp() {
        servico = new ServicoAutenticacao();
    }

    @Test
    void deveCadastrarMergulhadorComSucesso() {
        Mergulhador m = servico.cadastrar("João", "joao@email.com", "senha12345");
        assertNotNull(m);
        assertEquals("João", m.getNome());
    }

    @Test
    void deveLancarExcecaoAoCadastrarEmailDuplicado() {
        servico.cadastrar("João", "joao@email.com", "senha12345");
        assertThrows(RuntimeException.class, () -> {
            servico.cadastrar("Outro", "joao@email.com", "outrasenha");
        });
    }

    @Test
    void deveFazerLoginELogout() {
        servico.cadastrar("Maria", "maria@email.com", "senha12345");
        String sessao = servico.login("maria@email.com", "senha12345");
        assertNotNull(sessao);
        
        Mergulhador logado = servico.usuarioDaSessao(sessao);
        assertEquals("Maria", logado.getNome());
        
        servico.logout(sessao);
        assertThrows(AutenticacaoException.class, () -> servico.usuarioDaSessao(sessao));
    }

    @Test
    void deveFalharComSenhaErrada() {
        servico.cadastrar("Carlos", "carlos@email.com", "senha12345");
        assertThrows(AutenticacaoException.class, () -> {
            servico.login("carlos@email.com", "senha_errada");
        });
    }

    @Test
    void deveVerificarHashDeSenha() {
        String hash = Senhas.proteger("minhaSenhaSegura");
        assertTrue(Senhas.verificar("minhaSenhaSegura", hash));
        assertFalse(Senhas.verificar("senhaErrada", hash));
    }
}
