package sistema.projetos.sustentaveis.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import sistema.projetos.sustentaveis.dao.InvestimentoDAO;
import sistema.projetos.sustentaveis.dao.ProjetoDAO;
import sistema.projetos.sustentaveis.exception.ChaveEstrangeiraNaoEncontradaException;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Investimento;
import sistema.projetos.sustentaveis.vo.Projeto;

public class InvestimentoBO {

	// inserir
	public void inserirBO(Investimento investimento)
			throws ClassNotFoundException, SQLException, ChaveEstrangeiraNaoEncontradaException {

		InvestimentoDAO investimentoDAO = new InvestimentoDAO();

		// validação chave estrangeira
		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = projetoDAO.selecionarPorId(investimento.getId());

		if (projeto == null) {
			throw new ChaveEstrangeiraNaoEncontradaException("O Id não corresponde a nenhum projeto!");
		} else {
			investimentoDAO.inserir(investimento);
		}
	}

	// atualizar
	public void atualizarBO(Investimento investimento)
			throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {

		InvestimentoDAO investimentoDAO = new InvestimentoDAO();

		investimentoDAO.atualizar(investimento);
	}

	// deletar
	public void deletarBO(int id) throws ClassNotFoundException, SQLException {

		InvestimentoDAO investimentoDAO = new InvestimentoDAO();

		investimentoDAO.deletar(id);
	}

	// selecionar
	public ArrayList<Investimento> selecionarBO() throws ClassNotFoundException, SQLException {

		InvestimentoDAO investimentoDAO = new InvestimentoDAO();
		return (ArrayList<Investimento>) investimentoDAO.selecionar();
	}

	// selecionar por ID
	public Investimento selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
		InvestimentoDAO investimentoDAO = new InvestimentoDAO();
		return investimentoDAO.selecionarPorId(id);
	}

}
