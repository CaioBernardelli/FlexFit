package testejunit;

import static org.junit.Assert.*;
import org.junit.Test;

import modelo.Personal;
import regras_negocio.Fachada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class PersonalTest {

    @Test
    public void testCriarPersonal() throws Exception {
        // Cenário de teste: Criar um personal válido
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtualFormatada = dataAtual.format(formatter);
        
        Personal personal = Fachada.criarPersonal("Caio", dataAtualFormatada, 2000.0, "Segunda a Sexta, 08:00 - 17:00");
        assertNotNull(personal);
        assertEquals("João", personal.getNome());
        assertEquals(2000.0, personal.getSalario(), 0.01); // Comparação com tolerância de 0.01
        assertEquals("Segunda a Sexta, 08:00 - 17:00", personal.getHorario());
    }

    @Test(expected = Exception.class)
    public void testCriarPersonalDataInvalida() throws Exception {
        // Cenário de teste: Data inválida
        Fachada.criarPersonal("Maria", "01/1990", 1800.0, "Segunda a Sexta, 09:00 - 18:00");
    }

    @Test(expected = Exception.class)
    public void testCriarPersonalNomeExistente() throws Exception {
        // Cenário de teste: Nome já existente
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtualFormatada = dataAtual.format(formatter);
        
        Fachada.criarPersonal("João", dataAtualFormatada, 2500.0, "Segunda a Sexta, 10:00 - 19:00");
    }

    @Test(expected = Exception.class)
    public void testCriarPersonalHorarioInvalido() throws Exception {
        // Cenário de teste: Horário inválido
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtualFormatada = dataAtual.format(formatter);
        
        Fachada.criarPersonal("Pedro", dataAtualFormatada, 2200.0, "");
    }
}