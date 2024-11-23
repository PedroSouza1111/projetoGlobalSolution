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

import sistema.projetos.sustentaveis.bo.ImpactoBO;
import sistema.projetos.sustentaveis.dto.ImpactoDTO;
import sistema.projetos.sustentaveis.exception.ChaveEstrangeiraNaoEncontradaException;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Impacto;

@Path("/impacto")
public class ImpactoResource {

	private ImpactoBO impactoBO = new ImpactoBO();

	private ModelMapper modelMapper = new ModelMapper();

	// inserir (POST)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastroRs(ImpactoDTO impactoDTO, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException, ChaveEstrangeiraNaoEncontradaException {
		Impacto impacto = modelMapper.map(impactoDTO, Impacto.class);
		impactoBO.inserirBO(impacto);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(impacto.getId()));
		return Response.created(builder.build()).build();
	}

	// atualizar (PUT)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarRs(Impacto impacto, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {
		impactoBO.atualizarBO(impacto);
		return Response.ok().build();
	}

	// deletar (DELETE)
	@DELETE
	@Path("/{id}")
	public Response deletaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		impactoBO.deletarBO(id);
		return Response.ok().build();
	}

	// consultar (GET)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Impacto> selecionaRs() throws ClassNotFoundException, SQLException {
		return (ArrayList<Impacto>) impactoBO.selecionarBO();
	}

	// consultar por id (GET)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Impacto selecionaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		return impactoBO.selecionarPorIdBO(id);
	}
}
