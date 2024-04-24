package regras_negocio;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import daodb4o.DAO;
import daodb4o.DAOAluno;
import daodb4o.DAOFuncionario;
import daodb4o.DAOPersonal;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Personal;



public class Fachada {
	private Fachada() {}

	private static DAOFuncionario daofuncionario  = new DAOFuncionario();
	private static DAOAluno daoaluno = new DAOAluno();
	private static DAOPersonal daopersonal = new DAOPersonal();
	
	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Funcionario localizarFuncionario(String nome) throws Exception {
		Funcionario f = daofuncionario .read(nome);
		if (f == null) {
			throw new Exception("nome inexistente:" + nome);
		}
		return f;
	}
	
	
	
	public static Personal localizarPersonal(String nome) throws Exception {
		Personal p = daopersonal .read(nome);
		if (p == null) {
			throw new Exception("nome inexistente de Personal:" + nome);
		}
		return p;
	}
	
	public static Aluno localizarAluno(String nome) throws Exception {
		Aluno a = daoaluno.read(nome);
		if (a == null) {
		throw new Exception("nome inexistente:" + nome);
		}
		return a;
	}

	public static Funcionario criarFuncionario(String nome, double salario) throws Exception {
	    DAO.begin();
	    try {
	        // validar o salário
	        if (salario <= 0) {
	            DAO.rollback();
	            throw new Exception("Salário inválido: " + salario);
	        }
	    } catch (NumberFormatException e) {
	        DAO.rollback();
	        throw new Exception("Formato de salário inválido: " + salario);
	    }
	    Funcionario f = daofuncionario.read(nome);
	    if (f != null) {
	        DAO.rollback();
	        throw new Exception("Criar funcionário - nome já existe: " + nome);
	    }
	    f = new Funcionario(nome, salario);
	    daofuncionario.create(f);
	    DAO.commit();
	    return f;
	}


	public static Aluno criarAluno(String nome, double altura, double largura, String dtvencimentomatricula, String matricula) throws Exception {
	    DAO.begin();
	    try {
	        if (altura <= 0) {
	            DAO.rollback();
	            throw new Exception("Altura inválida: " + altura);
	        }
	        if (largura <= 0) {
	            DAO.rollback();
	            throw new Exception("Largura inválida: " + largura);
	        }
	        
	        if (nome == null || nome.trim().isEmpty()) {
	            DAO.rollback();
	            throw new Exception("Nome inválido: o nome não pode ser nulo ou vazio.");
	        }

	        Aluno a = daoaluno.read(nome);
	        if (a != null) {
	            DAO.rollback();
	            throw new Exception("Criar aluno - nome já existe: " + nome);
	        }

	        a = new Aluno(nome, altura, largura, dtvencimentomatricula, matricula); // Não é mais necessário converter a data aqui
	        System.out.println("Nome antes de criar : " + a.getNome()); 
	        daoaluno.create(a);
	        DAO.commit();
	        return a;
	    } catch (Exception e) {
	        DAO.rollback();
	        throw e; // Lança a exceção original sem modificação
	    }
	}



	public static Personal criarPersonal(String nome, String data, double salario, String horario) throws Exception {
	    DAO.begin();
	 
	    try {
	        LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    } catch (DateTimeParseException e) {
	        DAO.rollback();
	        throw new Exception("Formato de data inválido: " + data);
	    }

	    Personal ps = daopersonal.read(nome);
	    if (ps != null) {
	        DAO.rollback();
	        throw new Exception("Criar personal - nome já existe: " + nome);
	    }

	    if (horario == null || horario.isEmpty()) {
	        DAO.rollback();
	        throw new Exception("Horário inválido: " + horario);
	    }

	    ps  = new Personal(nome, salario, horario);
	    daopersonal.create(ps);
	    DAO.commit();
	    
	    return ps;
	   
	}


	public static void alterarHorarioPersonal(String nome, String novoHorario) throws Exception {
	    DAO.begin();
	    Personal ps = daopersonal.read(nome);
	    if (ps == null) {
	        DAO.rollback();
	        throw new Exception("Alterar horário - personal inexistente: " + nome);
	    }

	    if (novoHorario != null && !novoHorario.isEmpty()) {
	        ps.setHorario(novoHorario);
	    } else {
	        DAO.rollback();
	        throw new Exception("Horário inválido: " + novoHorario);
	    }

	    daopersonal.update(ps);
	    DAO.commit();
	}




	public static void excluirAluno(String nome) throws Exception {
	    DAO.begin();
	    Aluno a = daoaluno.read(nome);
	    if (a == null) {
	        DAO.rollback();
	        throw new Exception("Excluir aluno - nome inexistente: " + nome);
	    }

	    // desligar o aluno de suas dependências e apagar o aluno do banco
	    // Neste exemplo, supondo que o aluno não tem dependências adicionais, não precisamos de um loop.

	    daoaluno.delete(a); // apagar o aluno
	    DAO.commit();
	}


	public static void excluirFuncionario(String nome) throws Exception {
	    DAO.begin();
	    Funcionario funcionario = daofuncionario.read(nome);
	    if (funcionario == null) {
	        DAO.rollback();
	        throw new Exception("Excluir funcionário - nome inexistente: " + nome);
	    }

	    // Desligar o funcionário de suas dependências, se houver, e apagar o funcionário do banco
	    // Neste exemplo, supondo que o funcionário não tem dependências adicionais, não precisamos de um loop.

	    daofuncionario.delete(funcionario); // apagar o funcionário
	    DAO.commit();
	}

      
	public static void excluirPersonal(String nome) throws Exception {
	    DAO.begin();
	    Personal personal = daopersonal.read(nome);
	    if (personal == null) {
	        DAO.rollback();
	        throw new Exception("Excluir personal - nome inexistente: " + nome);
	    }

	    // Desligar o personal de suas dependências, se houver, e apagar o personal do banco
	    // Neste exemplo, supondo que o personal não tem dependências adicionais, não precisamos de um loop.

	    daopersonal.delete(personal); // apagar o personal
	    DAO.commit();
	}

	
	public static String gerarMatricula() {
	    // Aqui você pode implementar a lógica para gerar a matrícula, por exemplo:
	    // Concatenar um prefixo com um número aleatório
	    String prefixo = "MAT";
	    Random random = new Random();
	    int numeroAleatorio = random.nextInt(10000); // Pode ajustar o limite conforme sua necessidade
	    return prefixo + numeroAleatorio;
	}
  
	
	public static String gerarDataVencimentoMatricula()  {
		
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6); // Adiciona 6 meses à data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dtVencimentoMatricula = sdf.format(cal.getTime());
		return dtVencimentoMatricula;

    
    }

 

	

	public static List<Aluno> listarAlunos() {
		List<Aluno> result = daoaluno.readAll();
		return result;
	}

	public static List<Personal> listarProfessores() {
		List<Personal> result = daopersonal.readAll();
		return result;
	}

	public static List<Funcionario> listarTelefones() {
		List<Funcionario> result = daofuncionario.readAll();
		return result;
	}

	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS DAO
	 * 
	 **********************************************************/
	public static List<Aluno> consultarAlunosporMatricula(String caracteres) {
		List<Aluno> result;
		DAO.begin();
		if (caracteres.isEmpty())
			result = daoaluno.readAll();
		else
			result = daoaluno.readAll(caracteres);
		DAO.commit();
		return result;
	}

	public static List<Funcionario> consultarFuncionarioPeloNome(String caracteres) {
	    List<Funcionario> result;
	    DAO.begin();
	    if (caracteres.isEmpty()) {
	        result = daofuncionario.readAll();
	    } else {
	        // Consulta utilizando o método readll com o nome como chave
	        Funcionario funcionario = daofuncionario.readll(caracteres);
	        if (funcionario != null) {
	            result = new ArrayList<>();
	            result.add(funcionario);
	        } else {
	            result = new ArrayList<>(); // Ou null, dependendo da sua necessidade
	        }
	    }
	    DAO.commit();
	    return result;
	}





}
