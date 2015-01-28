package br.com.wcj.grupoestudos.envers.ws;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import br.com.wcj.grupoestudos.envers.modelo.Cliente;
import br.com.wcj.grupoestudos.envers.modelo.Endereco;
import br.com.wcj.grupoestudos.envers.modelo.Usuario;
import br.com.wcj.grupoestudos.envers.persistence.ClienteRepository;

@Path("/integracao")
@Stateless
public class Integracao {

    private static Logger logger = Logger.getLogger(Integracao.class);

    @Inject
    private ClienteRepository repository;

    @GET
    @Path("/createCliente")
    @Produces(MediaType.TEXT_PLAIN)
    public Response criarClientes(@Context HttpServletRequest request) {

        try {
            logger.info("[BEGIN] criando clientes");

            String dateString = "_" + new Date().toString();

            Cliente cliente = new Cliente();
            cliente.setCnpj("65628629000126");
            cliente.setRazaoSocial("Capelli LTDA");

            Endereco endereco = new Endereco();
            endereco.setLogradouro("Rua Aprigio de Araujo");
            endereco.setNumero(49L);
            endereco.setCep("14160030");
            endereco.setBairro("Centro");
            endereco.setCidade("Sertãozinho");
            endereco.setUf("SP");
            cliente.setEndereco(endereco);

            Usuario usuario = new Usuario();
            usuario.setCliente(cliente);
            usuario.setAtivo(Boolean.TRUE);
            usuario.setEmail("dadocapelli@gmail.com");
            usuario.setNome("Bernardo");
            usuario.setSobrenome("Capelli");
            cliente.getUsuarios().add(usuario);

            repository.persist(cliente);

            cliente = new Cliente();
            cliente.setCnpj("56389111000196");
            cliente.setRazaoSocial("WCJ Fabrica de Software");

            endereco = new Endereco();
            endereco.setLogradouro("Rua Jorge Fazolim");
            endereco.setNumero(270L);
            endereco.setCep("14096650");
            endereco.setBairro("Nova Ribeirania");
            endereco.setCidade("Ribeirão Preto");
            endereco.setUf("SP");
            cliente.setEndereco(endereco);

            usuario = new Usuario();
            usuario.setCliente(cliente);
            usuario.setAtivo(Boolean.TRUE);
            usuario.setEmail("suporte.digital@wba.com.br");
            usuario.setNome("Suporte");
            usuario.setSobrenome("Digital");
            cliente.getUsuarios().add(usuario);

            repository.persist(cliente);

            logger.info("[END] criando clientes");

            return Response.status(Status.OK).entity("OK " + dateString)
                    .build();

        } catch (Exception e) {
            logger.error(e);
            return Response.status(Status.BAD_REQUEST)
                    .entity(Status.BAD_REQUEST.getReasonPhrase()).build();
        }
    }

    @GET
    @Path("/updateCliente")
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizaClientes(@Context HttpServletRequest request,
            @QueryParam("id") String id) {

        try {
            logger.info("[BEGIN] atualiza clientes");

            String dateString = "_" + new Date().toString();

            Cliente cliente = repository.find(Long.valueOf(id)).get();
            cliente.setRazaoSocial(cliente.getRazaoSocial() + dateString);
            repository.update(cliente);

            logger.info("[END] atualiza clientes");

            return Response.status(Status.OK).entity("OK " + dateString)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return Response.status(Status.BAD_REQUEST)
                    .entity(Status.BAD_REQUEST.getReasonPhrase()).build();
        }
    }

    @GET
    @Path("/updateEndereco")
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizaEndereco(@Context HttpServletRequest request,
            @QueryParam("id") String id) {

        try {
            logger.info("[BEGIN] atualiza endereço");

            String dateString = "_" + new Date().toString();

            Cliente cliente = repository.find(Long.valueOf(id)).get();
            Long numero = cliente.getEndereco().getNumero() + 1;
            cliente.getEndereco().setNumero(numero);
            repository.update(cliente);

            logger.info("[END] atualiza endereço");

            return Response.status(Status.OK).entity("OK " + dateString)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return Response.status(Status.BAD_REQUEST)
                    .entity(Status.BAD_REQUEST.getReasonPhrase()).build();
        }
    }

    @GET
    @Path("/updateUsuario")
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizaUsuario(@Context HttpServletRequest request,
            @QueryParam("id") String id) {

        try {
            logger.info("[BEGIN] atualiza usuario");

            String dateString = "_" + new Date().toString();

            Cliente cliente = repository.find(Long.valueOf(id)).get();
            Usuario usuario = cliente.getUsuarios().get(0);
            usuario.setAtivo(!usuario.getAtivo());
            usuario.setSobrenome(usuario.getSobrenome() + dateString);
            repository.update(cliente);

            logger.info("[END] atualiza usuario");

            return Response.status(Status.OK).entity("OK " + dateString)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return Response.status(Status.BAD_REQUEST)
                    .entity(Status.BAD_REQUEST.getReasonPhrase()).build();
        }
    }

    @GET
    @Path("/audit")
    @Produces(MediaType.TEXT_PLAIN)
    public Response audit(@Context HttpServletRequest request) {

        try {
            logger.info("[BEGIN] audit");

            String dateString = "_" + new Date().toString();
            repository.buscaAudit(1L);

            logger.info("[END] audit");

            return Response.status(Status.OK).entity("OK " + dateString)
                    .build();

        } catch (Exception e) {
            logger.error(e);
            return Response.status(Status.BAD_REQUEST)
                    .entity(Status.BAD_REQUEST.getReasonPhrase()).build();
        }
    }

}
