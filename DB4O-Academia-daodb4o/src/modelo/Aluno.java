package modelo;

/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

public class Aluno {
	private String nome;
	private double altura;
	private double largura;
	private String dtvencimentomatricula ;	
	private String matricula;
	
	public Aluno(String nome, double altura, double largura, String matricula,String dtvencimentomatricula){
		;
		this.nome = nome;
		this.altura = altura;
		this.largura = largura;
		this.dtvencimentomatricula = dtvencimentomatricula;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public String getDtvencimentomatricula() {
		return dtvencimentomatricula;
	}

	public void setDtvencimentomatricula(String dtvencimentomatricula) {
		this.dtvencimentomatricula = dtvencimentomatricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", altura=" + altura + ", largura=" + largura + ", dtvencimentomatricula="
				+ dtvencimentomatricula + ", matricula=" + matricula + "]";
	}


	 
}