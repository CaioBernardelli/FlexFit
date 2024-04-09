package modelo;


public class Personal extends Funcionario {
  	private String horario;
	public Personal(String nome, double salario, String horario) {
		super(nome, salario);
		this.horario = horario;
		
	}

	  public String getHorario() {
			return horario;
		}
		public void setHorario(String horario) {
			this.horario = horario;
		}

		@Override
		public String toString() {
			return "Personal [horario=" + horario + ", toString()=" + super.toString() + "]";
		}

	
	
	}

	
	


