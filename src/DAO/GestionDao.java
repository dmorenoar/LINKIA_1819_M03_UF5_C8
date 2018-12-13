/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Excepciones.ExcepcionLigaPokemon;
import Modelos.Entrenador;
import Modelos.Pokemon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public void insertarEntrenador(Entrenador e) throws SQLException, ExcepcionLigaPokemon{
        
        if(existeEntrenador(e)){
            throw new ExcepcionLigaPokemon("ERROR: Entrenador ya existe en la BDD");
        }
            
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
    
    //Método para borrar un entrenador
    public void deleteEntrenador(Entrenador e) throws ExcepcionLigaPokemon, SQLException{
        
        if(!existeEntrenador(e)){
            throw new ExcepcionLigaPokemon("ERROR: Entrenador no existe en la BDD");
        }
        
        String delete = "Delete from Entrenador where nombre='" + e.getNombre() + "'";
        
        Statement st = conexion.createStatement();
        st.executeUpdate(delete);
        st.close();
        
    }
    
    
    public List<Entrenador> getAllEntrenadores() throws SQLException{
        List<Entrenador> entrenadores = new ArrayList();
     
        String select = "Select * From Entrenador";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        
        while(rs.next()){
            //nombre, telefono, sexo, edad, experiencia, especialidad
            Entrenador e = new Entrenador();
            e.setNombre(rs.getString("nombre"));
            e.setTelefono(rs.getString("telefono"));
            e.setEdad(rs.getInt("edad"));
            e.setExperiencia(rs.getInt("experiencia"));
            e.setEspecialidad(rs.getString("especialidad"));
            entrenadores.add(e);
            
        }
        
        rs.close();
        st.close();
        
    
        return entrenadores;
        
    }
    
    
    /*Gestión de Pokemons*/
    public void altaPokemon(Pokemon p ) throws SQLException, ExcepcionLigaPokemon{
        
        if(existePokemon(p)){
            throw new ExcepcionLigaPokemon("ERROR: Pokemon ya existe en la BDD");
        }
        
            // nombre tipo; fuerza; entrenador;
        
            
            String insert = "Insert into Pokemon (nombre, tipo, fuerza, entrenador) values(?,?,?,?)";
            
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setDouble(3, p.getFuerza());
            ps.setString(4, p.getEntrenador().getNombre());
            ps.executeUpdate();
            
            ps.close();
        
        
    }
    
    public boolean existePokemon (Pokemon p) throws SQLException{
        String select = "Select * from Pokemon where nombre ='" + p.getNombre() + "'";
    
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
   
    public void subirFuerzaPokemon(Pokemon p) throws SQLException, ExcepcionLigaPokemon{
        
        //Comprobar si existe el pokemon
        if(!existePokemon(p)){
            throw new ExcepcionLigaPokemon("ERROR: Pokemon no existe en la BDD");
        }
        
        String update = "Update Pokemon set fuerza = ? where nombre= '" + p.getNombre() + "'";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setDouble(1, p.getFuerza()); //ps.setDouble(1, 99);
        ps.executeUpdate();
        ps.close();
        
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
