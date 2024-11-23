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
import sistema.projetos.sustentaveis.vo.Impacto;

public class ImpactoDAO {

	public Connection minhaConexao;

	public ImpactoDAO() throws ClassNotFoundException, SQLException {
		this.minhaConexao = new ConexaoFactory().conexao();
	}

	// Método inserir()
	public void inserir(Impacto impacto) {
		String sql = "INSERT INTO tb_impacto (id_projeto, total_reducao_carbono, economia_gerada, impacto_total, dt_criacao) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setInt(1, impacto.getIdProjeto());
			stmt.setInt(2, impacto.getTotalReducaoCarbono());
			stmt.setInt(3, impacto.getEconomiaGerada());
			stmt.setInt(4, impacto.calculaImpacto());
			stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
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
	public String deletar(int id) {
		try {
			PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM tb_impacto WHERE ID_IMPACTO = ?");
			stmt.setInt(1, id);
			int linhas = stmt.executeUpdate();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Projeto não encontrado para ser removido");
			}
			stmt.close();
			return "Deletado com Sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao deletar!";
		}
	}

	// Update
	public void atualizar(Impacto impacto) throws EntidadeNaoEncontradaException {
		String sql = "UPDATE TB_IMPACTO SET id_projeto = ?, total_reducao_carbono = ?, economia_gerada = ?, impacto_total = ?, dt_atualizacao = ? WHERE id_impacto = ?";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setInt(1, impacto.getIdProjeto());
			stmt.setInt(2, impacto.getTotalReducaoCarbono());
			stmt.setInt(3, impacto.getEconomiaGerada());
			stmt.setInt(4, impacto.calculaImpacto());
			stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(6, impacto.getId());
			int linhas = stmt.executeUpdate();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Projeto não encontrado para ser atualizado");
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select
	public List<Impacto> selecionar() {
		try {
			List<Impacto> listaImpactos = new ArrayList<Impacto>();
			PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM tb_impacto");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Impacto impacto = new Impacto();
				impacto.setId(rs.getInt(1));
				impacto.setIdProjeto(rs.getInt(2));
				impacto.setTotalReducaoCarbono(rs.getInt(3));
				impacto.setEconomiaGerada(rs.getInt(4));
				impacto.setImpactoTotal(rs.getInt(5));
				impacto.setDataCriacao(rs.getTimestamp(6).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(7);
				if (timestampAtualizacao != null) {
					impacto.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					impacto.setDataAtualizacao(null);
				}
				listaImpactos.add(impacto);
			}
			return listaImpactos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar todos os impactos!");
			return null;
		}
	}

	// Select pelo id
	public Impacto selecionarPorId(int id) {
		try {
			Impacto impacto = null;
			PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM tb_impacto WHERE id_impacto = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				impacto = new Impacto();
				impacto.setId(rs.getInt(1));
				impacto.setIdProjeto(rs.getInt(2));
				impacto.setTotalReducaoCarbono(rs.getInt(3));
				impacto.setEconomiaGerada(rs.getInt(4));
				impacto.setImpactoTotal(rs.getInt(5));
				impacto.setDataCriacao(rs.getTimestamp(6).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(7);
				if (timestampAtualizacao != null) {
					impacto.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					impacto.setDataAtualizacao(null);
				}
			} else {
				throw new EntidadeNaoEncontradaException("Impacto não encontrado!");
			}
			return impacto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
