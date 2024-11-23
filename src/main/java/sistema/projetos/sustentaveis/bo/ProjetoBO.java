package sistema.projetos.sustentaveis.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import sistema.projetos.sustentaveis.dao.ProjetoDAO;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Projeto;

public class ProjetoBO {

	// inserir
	public void inserirBO(Projeto projeto) throws ClassNotFoundException, SQLException {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		projetoDAO.inserir(projeto);
	}

	// atualizar
	public void atualizarBO(Projeto projeto) throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		projetoDAO.atualizar(projeto);
	}

	// deletar
	public void deletarBO(int id) throws ClassNotFoundException, SQLException {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		projetoDAO.deletar(id);
	}

	// selecionar
	public ArrayList<Projeto> selecionarBO() throws ClassNotFoundException, SQLException {

		ProjetoDAO projetoDAO = new ProjetoDAO();
		return (ArrayList<Projeto>) projetoDAO.selecionar();
	}
	
	//selecionar por ID
    public Projeto selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        return projetoDAO.selecionarPorId(id);
    }

}
