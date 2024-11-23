package sistema.projetos.sustentaveis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sistema.projetos.sustentaveis.conexoes.ConexaoFactory;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Projeto;

public class ProjetoDAO {

	public Connection minhaConexao;

	public ProjetoDAO() throws ClassNotFoundException, SQLException {
		this.minhaConexao = new ConexaoFactory().conexao();
	}

	// Método inserir()
	public void inserir(Projeto projeto) {
		String sql = "INSERT INTO tb_projeto (nome_projeto, desc_projeto, tipo_energia, valor_meta, valor_arrecadado, dt_criacao) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setString(1, projeto.getNome());
			stmt.setString(2, projeto.getDescricaoProjeto());
			stmt.setString(3, projeto.getTipoEnergia());
			stmt.setFloat(4, projeto.getValorMeta());
			stmt.setFloat(5, projeto.getValorArrecadado());
			stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			if (minhaConexao == null) {
				System.err.println("Conexão nula!");
			}
			e.printStackTrace();
		}
	}

	// Delete
	public void deletar(int id) {
		try {
			PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM tb_projeto WHERE ID_PROJETO = ?");
			stmt.setInt(1, id);
			int linhas = stmt.executeUpdate();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Projeto não encontrado para ser removido");
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update
	public void atualizar(Projeto projeto) throws EntidadeNaoEncontradaException {
		String sql = "UPDATE tb_projeto SET nome_projeto = ?, desc_projeto = ?, tipo_energia = ?, valor_meta = ?, valor_arrecadado = ?, "
				+ "dt_atualizacao = ? WHERE id_projeto = ?";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setString(1, projeto.getNome());
			stmt.setString(2, projeto.getDescricaoProjeto());
			stmt.setString(3, projeto.getTipoEnergia());
			stmt.setFloat(4, projeto.getValorMeta());
			stmt.setFloat(5, projeto.getValorArrecadado());
			stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(7, projeto.getId());
			int linhas = stmt.executeUpdate();
			stmt.close();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Projeto não encontrado para ser atualizado");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select
	public List<Projeto> selecionar() {
		try {
			List<Projeto> listaProjetos = new ArrayList<Projeto>();
			PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM tb_projeto");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricaoProjeto(rs.getString(3));
				projeto.setTipoEnergia(rs.getString(4));
				projeto.setValorMeta(rs.getInt(5));
				projeto.setValorArrecadado(rs.getInt(6));
				projeto.setDataCriacao(rs.getTimestamp(7).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(8);
				if (timestampAtualizacao != null) {
					projeto.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					projeto.setDataAtualizacao(null);
				}
				listaProjetos.add(projeto);
			}
			return listaProjetos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar todos os projetos!");
			return null;
		}
	}

	// Select pelo id
	public Projeto selecionarPorId(int id) {
		try {
			Projeto projeto = null;
			PreparedStatement stmt = minhaConexao
					.prepareStatement("SELECT * FROM tb_projeto WHERE id_projeto = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				projeto = new Projeto();
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricaoProjeto(rs.getString(3));
				projeto.setTipoEnergia(rs.getString(4));
				projeto.setValorMeta(rs.getInt(5));
				projeto.setValorArrecadado(rs.getInt(6));
				projeto.setDataCriacao(rs.getTimestamp(7).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(8);
				if (timestampAtualizacao != null) {
					projeto.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					projeto.setDataAtualizacao(null);
				}
			}else {
				throw new EntidadeNaoEncontradaException("Projeto não encontrado!");
			}
			return projeto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
