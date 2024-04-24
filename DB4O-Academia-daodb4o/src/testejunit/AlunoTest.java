package testejunit;

import static org.junit.Assert.*;
import org.junit.Test;

import modelo.Aluno;
import regras_negocio.Fachada;

public class AlunoTest {


    @Test(expected = Exception.class)
    public void testCriarAlunoAlturaInvalida() throws Exception {
        // Cenário de teste: Altura inválida
    	Fachada.criarAluno("Maria", -1.0, 60.0, "2024-04-12", "54321");
    }

    @Test(expected = Exception.class)
    public void testCriarAlunoLarguraInvalida() throws Exception {
        // Cenário de teste: Largura inválida
    	Fachada.criarAluno("Pedro", 1.70, -50.0, "2024-04-12", "67890");
    }

    @Test(expected = Exception.class)
    public void testCriarAlunoNomeInvalido() throws Exception {
        // Cenário de teste: Nome inválido
    	Fachada.criarAluno("", 1.75, 65.0, "2024-04-12", "09876");
    }

    @Test(expected = Exception.class)
    public void testCriarAlunoNomeExistente() throws Exception {
        // Cenário de teste: Nome já existente
    	Fachada.criarAluno("João", 1.70, 65.0, "2024-04-12", "123");
    }
}