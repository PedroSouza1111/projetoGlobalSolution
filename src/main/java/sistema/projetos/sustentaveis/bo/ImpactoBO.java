package sistema.projetos.sustentaveis.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import sistema.projetos.sustentaveis.dao.ImpactoDAO;
import sistema.projetos.sustentaveis.dao.ProjetoDAO;
import sistema.projetos.sustentaveis.exception.ChaveEstrangeiraNaoEncontradaException;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Impacto;
import sistema.projetos.sustentaveis.vo.Projeto;

public class ImpactoBO {

	// inserir
	public void inserirBO(Impacto impacto)
			throws ClassNotFoundException, SQLException, ChaveEstrangeiraNaoEncontradaException {

		ImpactoDAO impactoDAO = new ImpactoDAO();

		// validação chave estrangeira
		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = projetoDAO.selecionarPorId(impacto.getId());

		if (projeto == null) {
			throw new ChaveEstrangeiraNaoEncontradaException("O Id não corresponde a nenhum projeto!");
		} else {
			impactoDAO.inserir(impacto);
		}
	}

	// atualizar
	public void atualizarBO(Impacto impacto)
			throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {

		ImpactoDAO impactoDAO = new ImpactoDAO();

		impactoDAO.atualizar(impacto);
	}

	// deletar
	public void deletarBO(int id) throws ClassNotFoundException, SQLException {

		ImpactoDAO impactoDAO = new ImpactoDAO();

		impactoDAO.deletar(id);
	}

	// selecionar
	public ArrayList<Impacto> selecionarBO() throws ClassNotFoundException, SQLException {

		ImpactoDAO impactoDAO = new ImpactoDAO();
		return (ArrayList<Impacto>) impactoDAO.selecionar();
	}

	// selecionar por ID
	public Impacto selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
		ImpactoDAO impactoDAO = new ImpactoDAO();
		return impactoDAO.selecionarPorId(id);
	}

}
