package sistema.projetos.sustentaveis.vo;

import java.time.LocalDateTime;

public class Impacto {

	private int id;
	private int idProjeto;
	private int totalReducaoCarbono;
	private int economiaGerada;
	private int impactoTotal;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;	
	
	//construtor
	public Impacto() {
		
	}
	
	//método para calcular o valor do impacto total do projeto
	public int calculaImpacto() {
		int impactoTotal = (totalReducaoCarbono * 80) + economiaGerada;
		return impactoTotal;
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

	public int getTotalReducaoCarbono() {
		return totalReducaoCarbono;
	}

	public void setTotalReducaoCarbono(int totalReducaoCarbono) {
		this.totalReducaoCarbono = totalReducaoCarbono;
	}

	public int getEconomiaGerada() {
		return economiaGerada;
	}

	public void setEconomiaGerada(int economiaGerada) {
		this.economiaGerada = economiaGerada;
	}

	public int getImpactoTotal() {
		return impactoTotal;
	}

	public void setImpactoTotal(int impactoTotal) {
		this.impactoTotal = impactoTotal;
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
