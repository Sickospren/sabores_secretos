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

import org.com.sabores_secretos.dao.consultas.DAO_UsuarioImpl;
import org.com.sabores_secretos.entities.Usuario;

@ApplicationScoped
@Path("/usuario")
public class RestUsuario {

    DAO_UsuarioImpl usuario = new DAO_UsuarioImpl();

    //metodo comentado por motivos de seguridad de la api
    //metodo que devuelve una lista con todos los usuarios
    //curl -X GET http://localhost:8080/sabores_secretos/servicios/user/listar    
    /*@GET
    @Path("/listar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarUsuarios() {

        List<Usuario> lista_usuarios;
        Response.Status responseStatus = Response.Status.OK;
        try {
            lista_usuarios = usuario.listar();

            if (lista_usuarios.size() < 1) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No hay usuarios").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if (responseStatus == Response.Status.OK) {
            return Response.ok(lista_usuarios).build();
        } else {
            return Response.status(responseStatus).build();
        }
    }
     */
    //curl -X POST -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\suario.json http://localhost:8080/sabores_secretos/servicios/usuario/crear
    //inserta un usuario dandole directamente un objeto
    @POST
    @Path("/crear")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearUsuarioObjeto(Usuario u) {
        try {

            if (!Utilidades.validarContrasena(u.getContrasena())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Datos no validos, error formato contraseña no valida").build();
            }

            String respuesta = usuario.registrarUser(u);
            if (respuesta.isEmpty()) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al Crear el usuario").build();
            } else {
                return Response.status(Response.Status.CREATED).entity("Usuario creado correctamente id usuario: " + respuesta).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el usuario").build();
        }

    }

    //metodo comentado por motivos de seguridad de la api
    /*
    //curl -XDELETE http://localhost:8080/sabores_secretos/servicios/usuario/borrar/juancarlitos
    //curl -XDELETE https://1358-83-33-193-69.ngrok-free.app/sabores_secretos/servicios/usuario/borrar/juancarlitos
    //Metodo para borrar un usuario usando su nombre como clave para borrar
    @DELETE
    @Path("/borrar/{nombre_usuario}")
    public Response deleteUsuario(@PathParam("nombre_usuario") String nombre_usuario) {

        Response.Status responseStatus = Response.Status.OK;
        try {
            if (!Utilidades.validarIdUsuario(nombre_usuario)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Datos no validos, error formato").build();
            }

            Usuario u = new Usuario();
            u.setNombre(nombre_usuario);
            int respuesta = usuario.eliminar(u);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el usuario").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Usuario borrado").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrar el usuario").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(responseStatus).build();
    }
     */
    
    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/usuario/modificar/contrasena/carlitos/carlitosContrasena
    @PUT
    @Path("/modificar/contrasena/{id_usuario}/{contrasena_nueva}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarContrasena(@PathParam("id_usuario") String id_usuario, @PathParam("contrasena_nueva") String contrasena_nueva) {

        try {
            
            if (!Utilidades.validarContrasena(contrasena_nueva)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Datos no validos, contraseña nueva").build();
            }

            Usuario u = new Usuario();
            u.setNombre(id_usuario);
            int respuesta = usuario.modificarContrasena(u, contrasena_nueva);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el usuario").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("usuario modificado correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el usuario").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/usuario/modificar/correo/carlitos/nuevoCarlitos@correo
    @PUT
    @Path("/modificar/correo/{id_usuario}/{correo_nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarCorreo(@PathParam("id_usuario") String id_usuario, @PathParam("correo_nuevo") String correo_nuevo) {

        try {

            Usuario u = new Usuario();
            u.setNombre(id_usuario);
            int respuesta = usuario.modificarContrasena(u, correo_nuevo);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el usuario").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("usuario modificado correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el usuario").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/usuario/modificar/baja/masterChief
    @PUT
    @Path("/modificar/baja/{id_usuario}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deshabilitarUsuario(@PathParam("id_usuario") String id_usuario) {

        try {
            Usuario u = new Usuario();
            u.setNombre(id_usuario);
            int respuesta = usuario.bajaUsuario(id_usuario);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el usuario").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("baja de usuario correcta").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el usuario").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/usuario/modificar/habilitar/masterChief/jefemaestro
    @PUT
    @Path("/modificar/habilitar/{id_usuario}/{contrasena}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response habilitarUsuario(@PathParam("id_usuario") String id_usuario, @PathParam("contrasena") String contrasena) {

        try {
            Usuario u = new Usuario();
            u.setNombre(id_usuario);
            int respuesta = usuario.habilitarUsuario(id_usuario, contrasena);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("Error de credenciales").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("usuario habilitado de nuevo").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el usuario").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //curl -XGET http://localhost:8080/sabores_secretos/servicios/usuario/buscar/carlitos
    //curl -XGET http://localhost:8080/sabores_secretos/servicios/usuario/buscar/carlitos@example.com
    //este metodo funciona como para buscar por usuario o por correo electronico ya que ambos son unicos
    @GET
    @Path("/buscar/{dato}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response selectUsuario(@PathParam("dato") String dato) {

        try {
            Usuario u = usuario.encontrarUsuario(dato);
            if (u.getNombre().isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado al usuario").build();
            } else {
                return Response.ok(u).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    //curl -XGET http://localhost:8080/sabores_secretos/servicios/usuario/login/carlitos/carlitosPASS
    @GET
    @Path("/login/{nombre}/{contrasena}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login(@PathParam("nombre") String nombre, @PathParam("contrasena") String contrasena) {

        try {
            boolean valido = usuario.login(nombre, contrasena);
            if (valido) {
                return Response.status(Response.Status.OK).entity(true).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity(false).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
