package sistema.projetos.sustentaveis.resources;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.modelmapper.ModelMapper;

import sistema.projetos.sustentaveis.bo.InvestimentoBO;
import sistema.projetos.sustentaveis.dto.InvestimentoDTO;
import sistema.projetos.sustentaveis.exception.ChaveEstrangeiraNaoEncontradaException;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Investimento;

@Path("/investimento")
public class InvestimentoResource {

	private InvestimentoBO investimentoBO = new InvestimentoBO();

	private ModelMapper modelMapper = new ModelMapper();

	// inserir (POST)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastroRs(InvestimentoDTO investimentoDTO, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException, ChaveEstrangeiraNaoEncontradaException {
		Investimento investimento = modelMapper.map(investimentoDTO, Investimento.class);
		investimentoBO.inserirBO(investimento);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(investimento.getId()));
		return Response.created(builder.build()).build();
	}

	// atualizar (PUT)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarRs(Investimento investimento, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {
		investimentoBO.atualizarBO(investimento);
		return Response.ok().build();
	}

	// deletar (DELETE)
	@DELETE
	@Path("/{id}")
	public Response deletaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		investimentoBO.deletarBO(id);
		return Response.ok().build();
	}

	// consultar (GET)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Investimento> selecionaRs() throws ClassNotFoundException, SQLException {
		return (ArrayList<Investimento>) investimentoBO.selecionarBO();
	}

	// consultar por id (GET)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Investimento selecionaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		return investimentoBO.selecionarPorIdBO(id);
	}
}
