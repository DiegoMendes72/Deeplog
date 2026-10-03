package br.com.deeplog;

import br.com.deeplog.autenticacao.*;
import br.com.deeplog.model.*;
import java.time.LocalDateTime;
import java.util.UUID;

/** Testes independentes de biblioteca externa. Executar com java -ea. */
public class AutenticacaoTest {
    public static void main(String[] args) {
        ServicoAutenticacao auth = new ServicoAutenticacao();
        Mergulhador ana = auth.cadastrar("Ana", " ANA@example.com ", "senhaAna123");
        auth.cadastrar("Bia", "bia@example.com", "senhaBia123");
        assert ana.verificarSenha("senhaAna123");
        assert !ana.verificarSenha("errada");
        assert !ana.verificarSenha(null);
        falha(IllegalArgumentException.class, () -> auth.cadastrar("Outra", "ana@EXAMPLE.com", "outraSenha123"));
        falha(IllegalArgumentException.class, () -> auth.cadastrar("Outra", "outra@example.com", "curta"));
        falha(IllegalArgumentException.class, () -> auth.cadastrar("Outra", "invalido", "outraSenha123"));
        falha(SecurityException.class, () -> auth.login("ana@example.com", "errada"));
        falha(SecurityException.class, () -> auth.login("ausente@example.com", "senhaAna123"));
        falha(SecurityException.class, () -> auth.usuarioDaSessao(null));
        String sessaoAna = auth.login("ana@example.com", "senhaAna123");
        String sessaoBia = auth.login("bia@example.com", "senhaBia123");
        assert !sessaoAna.equals(sessaoBia);
        ServicoRegistros registros = new ServicoRegistros(auth);
        PerfilMergulho perfil = new PerfilMergulho(new PontoMergulho("Ponto", "Local", "Descrição", "Condições"), "Tipo", 10);
        UUID id = registros.registrar(sessaoAna, perfil, LocalDateTime.now(), 30, 8);
        assert registros.consultar(sessaoAna, id).getAutor() == ana;
        assert registros.listar(sessaoAna).size() == 1;
        assert registros.listar(sessaoBia).isEmpty();
        assert ana.getQuantidadeMergulhos() == 1;
        falha(SecurityException.class, () -> registros.consultar(sessaoBia, id));
        auth.logout(sessaoAna);
        falha(SecurityException.class, () -> registros.listar(sessaoAna));
        falha(SecurityException.class, () -> registros.registrar(sessaoAna, perfil, LocalDateTime.now(), 30, 8));
        assert auth.usuarioDaSessao(sessaoBia) != null;
        String hash1 = Senhas.proteger("mesmaSenha123");
        String hash2 = Senhas.proteger("mesmaSenha123");
        assert !hash1.equals(hash2);
        assert Senhas.verificar("mesmaSenha123", hash1);
        assert !Senhas.verificar("mesmaSenha123", "hash12345");
        System.out.println("Testes de autenticação e isolamento concluídos com sucesso.");
    }

    private static void falha(Class<? extends Exception> tipo, Runnable operacao) {
        try {
            operacao.run();
            throw new AssertionError("Era esperada uma exceção " + tipo.getSimpleName());
        } catch (Exception e) {
            if (!tipo.isInstance(e)) throw new AssertionError("Exceção inesperada", e);
        }
    }
}
