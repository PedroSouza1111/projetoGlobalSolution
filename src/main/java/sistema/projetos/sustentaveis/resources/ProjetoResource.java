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

import sistema.projetos.sustentaveis.bo.ProjetoBO;
import sistema.projetos.sustentaveis.dto.ProjetoDTO;
import sistema.projetos.sustentaveis.exception.EntidadeNaoEncontradaException;
import sistema.projetos.sustentaveis.vo.Projeto;

@Path("/projeto")
public class ProjetoResource {

	private ProjetoBO projetoBO = new ProjetoBO();

	private ModelMapper modelMapper = new ModelMapper();

	// inserir (POST)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastroRs(ProjetoDTO projetoDTO, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException {
		Projeto projeto = modelMapper.map(projetoDTO, Projeto.class);
		projetoBO.inserirBO(projeto);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(projeto.getId()));
		return Response.created(builder.build()).build();
	}

	// atualizar (PUT)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarRs(Projeto projeto, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException {
		projetoBO.atualizarBO(projeto);
		return Response.ok().build();
	}

	// deletar (DELETE)
	@DELETE
	@Path("/{id}")
	public Response deletaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		projetoBO.deletarBO(id);
		return Response.ok().build();
	}

	// consultar (GET)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Projeto> selecionaRs() throws ClassNotFoundException, SQLException {
		return (ArrayList<Projeto>) projetoBO.selecionarBO();
	}

	// consultar por id (GET)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Projeto selecionaRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		return projetoBO.selecionarPorIdBO(id);
	}
}
