package sistema.projetos.sustentaveis.dto;

public class ProjetoDTO {

	private String nome;
	private String descricaoProjeto;
	private String tipoEnergia;
	private float valorMeta;
	private float valorArrecadado;
	
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
	
	
	
}
