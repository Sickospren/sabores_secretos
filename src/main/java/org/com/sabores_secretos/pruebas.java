/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.com.sabores_secretos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.com.sabores_secretos.dao.consultas.DAO_CantidadesImpl;
import org.com.sabores_secretos.dao.consultas.DAO_UsuarioImpl;
import org.com.sabores_secretos.entities.Cantidades;
import org.com.sabores_secretos.entities.Ingrediente;
import org.com.sabores_secretos.entities.Receta;
import org.com.sabores_secretos.entities.Usuario;

/**
 *
 * @author lopez
 */
public class pruebas {

    public static void main(String args[]) {

        /*
    DAO_Usuario DAO_USU = new DAO_Usuario();
        Usuario u = new Usuario("marcos1234", "pass123", "marcos11231@example.com");
        
        //respuesta[0] = filas afectadas, respuesta[1] = id generada al insertar en la BD
        String respuesta = DAO_USU.create(u);
        
        System.out.println("Respuesta: "+ respuesta);

        if(respuesta.equals("error")){
            System.out.println("Usuario no creado");
        }else if(respuesta.equals("error_sql")){
            System.out.println("Error sql");
        }else if(respuesta.equals("ya_creado")){
            System.out.println("Usuario ya existente");
        }else{
            System.out.println("Usuario creado correctamente, ID de usuario : " +respuesta);
        }
         */
        //DAO_Ingrediente dao = new DAO_Ingrediente();
        /*
        try {
            int id = dao.existe("alcaparras");
            System.out.println("id ingrediente:  "+id);
        } catch (Exception ex) {
            System.out.println("error fatal");
        }*/
 /*
        try {
            List<Ingrediente> lista = dao.list();
            for (Ingrediente i : lista) {
                //System.out.println(i.getNombre());
            }
        } catch (Exception ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            int clave = dao.existe("Ajo");
            System.out.println("caaaaaaaaaaaaaa: "+clave);
        } catch (Exception ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
 /*
        DAO_Receta dao = new DAO_Receta();
        try {
            List<Receta> lista = dao.list();

            for (Receta r : lista) {
                System.out.println(r.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
         
 
         DAO_CantidadesImpl dao = new DAO_CantidadesImpl();
         
         try{
             Cantidades c = new Cantidades();
             c.setId_ingrediente(1);
             c.setId_receta(2);
             c.setCantidad(50);
             c.setUnidadMedida("gramos");
             dao.registrar(c);
             System.out.println("insertado");
         
         }catch(Exception e){
             System.out.println("Error");
             
             
         }
         
 */
        DAO_UsuarioImpl u = new DAO_UsuarioImpl();
        
        
        try{
            Usuario usu = new Usuario();
            usu.setNombre("aaa");
            usu.setCorreo("masterChief@example.com");
            usu.setContrasena("jefemaestro");
            
            int respuesta = u.habilitarUsuario(usu.getCorreo(),usu.getContrasena());
            
            System.out.println("aaaaaaaaaaa"+respuesta);

         }catch(Exception e){
             System.out.println("Error");
        }
       
        
    }     
    

}
