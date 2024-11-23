package sistema.projetos.sustentaveis.vo;

import java.time.LocalDateTime;

public class Projeto {

	private int id;
	private String nome;
	private String descricaoProjeto;
	private String tipoEnergia;
	private float valorMeta;
	private float valorArrecadado;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	
	//construtor
	public Projeto() {
	
	}
	
	//getters e setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}
	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
	}
	public String getTipoEnergia() {
		return tipoEnergia;
	}
	public void setTipoEnergia(String tipoEnergia) {
		this.tipoEnergia = tipoEnergia;
	}
	public float getValorMeta() {
		return valorMeta;
	}
	public void setValorMeta(float valorMeta) {
		this.valorMeta = valorMeta;
	}
	public float getValorArrecadado() {
		return valorArrecadado;
	}
	public void setValorArrecadado(float valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
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
