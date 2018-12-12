/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Entrenador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Daniel Moreno
 */

/*Data Access Object -> Lo llamamos así porque estamos accediendo a objetos,
que a su vez estamos transformando para evitar el desfase objeto-relacional*/
public class GestionDao {
    
    
    /*EL NO SE ENSUCIA LAS MANOS SOLO HACE MÉTODOS QUE RECIBEN Y DEVUELVEN ALGO(SI SE NECESITA)*/
    /*
    CRUD
    C -> CREATE
    R -> READ
    U -> UPDATE
    D -> DETELE
    */
    
    Connection conexion;
    
    
    /*Métodos de entrenador*/
    public boolean existeEntrenador(Entrenador e) throws SQLException{
        String select = "Select * from Entrenador where nombre ='" + e.getNombre() + "'";
    
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        
        boolean existe = false;
        
        if(rs.next()){
            existe = true;
        }
        
        rs.close();
        st.close();
        
        return existe;
        
    }
    
    public void insertarEntrenador(Entrenador e) throws SQLException{
        
        if(existeEntrenador(e)){
            //Gestionar con excepciones.
            System.out.println("Entrenador existe");
        }else{
            
            String insert = "Insert into Entrenador (nombre, telefono, sexo, edad, experiencia, especialidad) values(?,?,?,?,?,?)";
            
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTelefono());
            ps.setString(3, e.getSexo());
            ps.setInt(4, e.getEdad());
            ps.setInt(5, e.getExperiencia());
            ps.setString(6, e.getEspecialidad());
            
            ps.executeUpdate();
            
            ps.close();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void conectar() throws SQLException{
        
        String url= "jdbc:mysql://localhost:3306/ligapokemon";
        String user = "root";
        String pass = "";
        
        conexion = DriverManager.getConnection(url, user, pass);
    }
            
    public void desconectar() throws SQLException{
        
        if(conexion != null){
           conexion.close();
        }
        
    }
    
}
