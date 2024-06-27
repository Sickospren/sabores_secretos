package org.com.sabores_secretos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import org.com.sabores_secretos.dao.consultas.DAO_CantidadesImpl;
import org.com.sabores_secretos.entities.Cantidades;



@ApplicationScoped
@Path("/cantidades")
public class RestCantidades {

    DAO_CantidadesImpl cantidades = new DAO_CantidadesImpl();

    //curl -XGET http://localhost:8080/sabores_secretos/servicios/cantidades/listar/1
    @GET
    @Path("/listar/{id_receta}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarCantidades(@PathParam("id_receta") int id_receta) {
        List<Cantidades> lista_cantidades;

        try {
            lista_cantidades = cantidades.listarDetalle(id_receta);

            if (lista_cantidades.size() < 1) {
                return Response.status(Response.Status.NOT_FOUND).entity("No hay cantidades").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.ok(lista_cantidades).build();

    }

    //curl -X POST -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\cantidad.json http://localhost:8080/sabores_secretos/servicios/cantidades/crear
    @POST
    @Path("/crear")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearCantidades(Cantidades c) {

        try {
            int respuesta = cantidades.registrar(c);

            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.CREATED).entity("Cantidad creada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear la cantidad").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear la cantidad").build();
        }
        return Response.status(Response.Status.OK).build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\cantidad.json https://b2a0-83-33-193-69.ngrok-free.app/sabores_secretos/servicios/cantidades/modificar/cantidad/100
    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\cantidad.json http://localhost:8080/sabores_secretos/servicios/cantidades/modificar/cantidad/100
    @PUT
    @Path("/modificar/cantidad/{nueva_cantidad}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarCantidades(@PathParam("nueva_cantidad") int nueva_cantidad, Cantidades c) {

        try {
            int respuesta = cantidades.modificarCantidad(c, nueva_cantidad);

            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.OK).entity("Modificado").build();
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la cantidad").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la cantidad").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la cantidad").build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\cantidad.json http://localhost:8080/sabores_secretos/servicios/cantidades/modificar/unidad/toneladas
    @PUT
    @Path("/modificar/unidad/{nueva_unidad}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarUnidadMedida(@PathParam("nueva_unidad") String nueva_unidad, Cantidades c) {

        try {
            int respuesta = cantidades.modificarUnidadMedida(c, nueva_unidad);
            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.OK).entity("Modificado").build();
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la cantidad").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la unidad de cantidad").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la cantidad").build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -X DELETE http://localhost:8080/sabores_secretos/servicios/cantidades/borrar/1/2
    @DELETE
    @Path("/borrar/{id_receta}/{id_ingrediente}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response borrarCantidad(@PathParam("id_receta") int id_receta, @PathParam("id_ingrediente") int id_ingrediente) {

        try {
            Cantidades c = new Cantidades();
            c.setId_receta(id_receta);
            c.setId_ingrediente(id_ingrediente);
            int respuesta = cantidades.eliminar(c);

            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.OK).entity("Borrado").build();
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la cantidad").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrar cantidad").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrar cantidad").build();

        }
        return Response.status(Response.Status.OK).build();
    }

}
