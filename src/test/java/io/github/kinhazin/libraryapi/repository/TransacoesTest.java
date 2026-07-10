package io.github.kinhazin.libraryapi.repository;

import io.github.kinhazin.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    TransacaoService transacaoService;

    @Test
     //Commit ou rollback, commitar as alterações ou desfazer
    void transacaoSimples(){
    transacaoService.executar();
    }

    @Test
    void transacaoAtualizarSemAtualizar(){
        transacaoService.atualizacaoSemAtualizar();
    }
}
