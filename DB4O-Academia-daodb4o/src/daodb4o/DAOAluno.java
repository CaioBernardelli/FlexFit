
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Aluno;


public class DAOAluno  extends DAO<Aluno>{
	
	//nome � usado como campo unico  //excluir 
	public Aluno read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Aluno.class);
		q.descend("nome").constrain(nome);
		List<Aluno> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	
	public  List<Aluno> readAll(String caracteres) {
		Query q = manager.query();
		q.constrain(Aluno.class);
		q.descend("matricula").constrain(caracteres).like();		//insensitive
		List<Aluno> result = q.execute(); 
		return result;
	}
	
	
	
	
}

