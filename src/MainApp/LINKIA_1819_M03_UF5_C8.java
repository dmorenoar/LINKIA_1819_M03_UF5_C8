/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;

import DAO.GestionDao;
import Modelos.Entrenador;
import java.sql.SQLException;

/**
 *
 * @author Daniel Moreno
 */
public class LINKIA_1819_M03_UF5_C8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        GestionDao ligaDAO = new GestionDao();
        
        //Establecer al conexion
        conectar(ligaDAO);
        //Testeando insertar un entrenador
        Entrenador ash = new Entrenador("Ash", "+3412345", "Hombre", 2, 2, "Eléctrico");
        insertarEntrenador(ligaDAO, ash);
        
        //Testeando insertar un entrenador repetido
        
        //Testeando mostrar la lista de entrenadores
        
        //Testeando insertar un pokemon
        
        //Testeando insertar un pokemon con un entrenador que no existe
        
        //Testeando borrar un pokemon
        
        //Testeando buscar un entrenador por nombre
        
        //Testeando subir la fuerza de un pokemon
        
        //Desconectar
        desconectar(ligaDAO);
        
        
    }
    
    public static void conectar(GestionDao ligaDAO) throws SQLException{
        System.out.println("Conectando...");      
        ligaDAO.conectar();
    }
    
    
    public static void desconectar(GestionDao ligaDAO) throws SQLException{
        System.out.println("Desconectando...");
        ligaDAO.desconectar();
        System.out.println("Desconexión realizada.");
    }
    
    public static void insertarEntrenador(GestionDao ligaDao, Entrenador e) throws SQLException{
        ligaDao.insertarEntrenador(e);
        System.out.println("Entrenador insertado correctamente");
    }
    
    
}
