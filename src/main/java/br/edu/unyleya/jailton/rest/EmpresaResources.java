package br.edu.unyleya.jailton.rest;

import br.edu.unyleya.jailton.models.Empresa;
import br.edu.unyleya.jailton.models.EmpresaCampos;
import br.edu.unyleya.jailton.repositories.EmpresaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/empresa")
@RequestScoped
public class EmpresaResources {
    @Inject
    EmpresaRepository empresaRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        return Response.status(Response.Status.OK).entity(empresaRepository.listarTodas()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar Empresa Por ID",
            description = "Retorna uma empresa de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Empresa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Empresa.class, type = SchemaType.OBJECT))})
    public Response consultar(final @PathParam("id") Long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(empresaRepository.consultar(id)).build();
    }

    @POST
    @Operation(summary = "Criar uma empresa.",
            description = "Criar uma empresa.")
    @APIResponse(
            responseCode = "201",
            description = "Empresa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Empresa.class, type = SchemaType.OBJECT))})
    @Transactional
    public Response cadastrar(EmpresaCampos empresaCampos) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa(empresaCampos.getNomeEmpresa());
        empresa.setEndereco(empresaCampos.getEndereco());
        return  Response.status(Response.Status.CREATED).entity(empresaRepository.cadastrar(empresa)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir uma empresa.",
            description = "Excluir uma empresa.")
    @APIResponse(
            responseCode = "201",
            description = "Empresa",
            content = { @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class, type = SchemaType.OBJECT))})
    @Transactional
    public Response excluir(final @PathParam("id") Long id) throws Exception  {
        empresaRepository.deletar(id);
        return  Response.status(Response.Status.OK).entity("Empresa excluida com Sucesso.").build();
    }

    @PUT
    @Operation(summary = "Alterar uma empresa.",
            description = "Alterar uma empresa.")
    @APIResponse(
            responseCode = "202",
            description = "Empresa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Empresa.class, type = SchemaType.OBJECT))})
    @Transactional
    public Response alterar(Empresa empresa) throws Exception  {
        return  Response.status(Response.Status.ACCEPTED).entity(empresaRepository.alterar(empresa)).build();
    }
}