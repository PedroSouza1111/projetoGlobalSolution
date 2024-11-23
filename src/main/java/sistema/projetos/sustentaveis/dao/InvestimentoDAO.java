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
import sistema.projetos.sustentaveis.vo.Investimento;

public class InvestimentoDAO {

	public Connection minhaConexao;

	public InvestimentoDAO() throws ClassNotFoundException, SQLException {
		this.minhaConexao = new ConexaoFactory().conexao();
	}

	// Método inserir()
	public void inserir(Investimento investimento) {
		String sql = "INSERT INTO tb_investimento (id_projeto, valor_investimento, estimativa_retorno, dt_criacao) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setInt(1, investimento.getIdProjeto());
			stmt.setFloat(2, investimento.getValorInvestimento());
			stmt.setFloat(3, investimento.calcularEstimativaRetorno());
			stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
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
			PreparedStatement stmt = minhaConexao
					.prepareStatement("DELETE FROM tb_investimento WHERE ID_INVESTIMENTO = ?");
			stmt.setInt(1, id);
			int linhas = stmt.executeUpdate();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Investimento não encontrado para ser removido");
			}
			stmt.close();
			return "Deletado com Sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao deletar!";
		}
	}

	// Update
	public void atualizar(Investimento investimento) throws EntidadeNaoEncontradaException {
		String sql = "UPDATE TB_INVESTIMENTO SET id_projeto = ?, valor_investimento = ?,	 estimativa_retorno = ?, dt_atualizacao = ? WHERE id_investimento = ?";

		try {
			PreparedStatement stmt = minhaConexao.prepareStatement(sql);
			stmt.setInt(1, investimento.getIdProjeto());
			stmt.setFloat(2, investimento.getValorInvestimento());
			stmt.setFloat(3, investimento.calcularEstimativaRetorno());
			stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(5, investimento.getId());
			int linhas = stmt.executeUpdate();
			if (linhas == 0) {
				throw new EntidadeNaoEncontradaException("Investimento não encontrado para ser atualizado");
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select
	public List<Investimento> selecionar() {
		try {
			List<Investimento> listaInvestimentos = new ArrayList<Investimento>();
			PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM tb_investimento");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Investimento investimento = new Investimento();
				investimento.setId(rs.getInt(1));
				investimento.setIdProjeto(rs.getInt(2));
				investimento.setValorInvestimento(rs.getFloat(3));
				investimento.setEstimativaRetorno(rs.getFloat(4));
				investimento.setDataCriacao(rs.getTimestamp(5).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(6);
				if (timestampAtualizacao != null) {
					investimento.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					investimento.setDataAtualizacao(null);
				}
				listaInvestimentos.add(investimento);
			}
			return listaInvestimentos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar todos os investimentos!");
			return null;
		}
	}

	// Select pelo id
	public Investimento selecionarPorId(int id) {
		try {
			Investimento investimento = null;
			PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM tb_investimento WHERE id_projeto = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				investimento = new Investimento();
				investimento.setId(rs.getInt(1));
				investimento.setIdProjeto(rs.getInt(2));
				investimento.setValorInvestimento(rs.getFloat(3));
				investimento.setEstimativaRetorno(rs.getFloat(4));
				investimento.setDataCriacao(rs.getTimestamp(5).toLocalDateTime());
				Timestamp timestampAtualizacao = rs.getTimestamp(6);
				if (timestampAtualizacao != null) {
					investimento.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
				} else {
					investimento.setDataAtualizacao(null);
				}
			} else {
				throw new EntidadeNaoEncontradaException("Investimento não encontrado!");
			}
			return investimento;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
