

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Funcionario;
import modelo.Personal;

public class DAOPersonal  extends DAO<Personal>{
	//nome � usado como campo unico 
	public Personal read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Personal.class);
		q.descend("nome").constrain(nome);
		List<Personal> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}


}

