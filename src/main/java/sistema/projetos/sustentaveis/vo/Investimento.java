package sistema.projetos.sustentaveis.vo;

import java.time.LocalDateTime;

public class Investimento {

	private int id;
	private int idProjeto;
	private float valorInvestimento;
	private float estimativaRetorno;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	
	//construtor
	public Investimento() {
		
	}
	
	//método para calcular estimativaRetorno
	public float calcularEstimativaRetorno() {
		float ganhoPercentual = (float) ((valorInvestimento) * (0.15));
	    estimativaRetorno = valorInvestimento + ganhoPercentual;
	    return estimativaRetorno;
	}
	
	//getters e setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public float getValorInvestimento() {
		return valorInvestimento;
	}
	public void setValorInvestimento(float valorInvestimento) {
		this.valorInvestimento = valorInvestimento;
	}
	public float getEstimativaRetorno() {
		return estimativaRetorno;
	}
	public void setEstimativaRetorno(float estimativaRetorno) {
		this.estimativaRetorno = estimativaRetorno;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	
}
