package br.com.deeplog.persistencia;

import br.com.deeplog.excecoes.PersistenciaException;
import br.com.deeplog.model.RegistroMergulho;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArquivo {
    private final Path caminhoArquivo;

    public RepositorioArquivo(String caminho) {
        this.caminhoArquivo = Path.of(caminho);
    }

    // Salva os registros em um arquivo de texto (CSV simples)
    public void salvarRegistros(List<RegistroMergulho> registros) {
        List<String> linhas = new ArrayList<>();
        linhas.add("ID;Autor;Ponto;DataHora;DuracaoMin;ProfundidadeMaxima");
        
        for (RegistroMergulho r : registros) {
            String local = r.getPonto() != null ? r.getPonto().getNome() : r.getLocalInformado();
            String linha = String.format("%s;%s;%s;%s;%d;%.2f",
                r.getId(),
                r.getAutor().getNome(),
                local,
                r.getDataHora(),
                r.getDuracaoMin(),
                r.getProfundidadeMaximaM()
            );
            linhas.add(linha);
        }
        
        try {
            Files.write(caminhoArquivo, linhas);
        } catch (IOException e) {
            throw new PersistenciaException("Falha ao salvar registros no arquivo: " + caminhoArquivo, e);
        }
    }

    // Lê as linhas do arquivo
    public List<String> lerLinhas() {
        try {
            if (!Files.exists(caminhoArquivo)) {
                return List.of();
            }
            return Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            throw new PersistenciaException("Falha ao ler o arquivo: " + caminhoArquivo, e);
        }
    }
}
