

package daodb4o;


import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Funcionario;


public class DAOFuncionario  extends DAO<Funcionario>{

	//nome � usado como campo unico //excluir
	public Funcionario read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Funcionario.class);
		q.descend("nome").constrain(nome);
		List<Funcionario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}


	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE PESSOA
	 * 
	 **********************************************************/
//Consulta de Funcionario por nome:

	public Funcionario readll(Object chave) {
	    String nome = (String) chave;  // casting para o tipo da chave
	    Query q = manager.query();
	    q.constrain(Funcionario.class);
	    q.descend("nome").constrain(nome);
	    List<Funcionario> resultados = q.execute();
	    if (resultados.size() > 0)
	        return resultados.get(0);
	    else
	        return null;
	}

	//Consulta de Funcionarios por salário:
	public List<Funcionario> readBySalario(double salario) {
	    Query q = manager.query();
	    q.constrain(Funcionario.class);
	    q.descend("salario").constrain(salario);
	    List<Funcionario> resultados = q.execute();
	    return resultados;
	}

//Consulta de Funcionarios com salário maior que um valor específico:
	public List<Funcionario> readBySalarioMaiorQue(double salario) {
	    Query q = manager.query();
	    q.constrain(Funcionario.class);
	    q.descend("salario").constrain(salario).greater();
	    List<Funcionario> resultados = q.execute();
	    return resultados;
	}

}

