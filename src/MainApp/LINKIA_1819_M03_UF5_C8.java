/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;

import DAO.GestionDao;
import Excepciones.ExcepcionLigaPokemon;
import Modelos.Entrenador;
import Modelos.Pokemon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Moreno
 */
public class LINKIA_1819_M03_UF5_C8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GestionDao ligaDAO = new GestionDao();

        try {

            //Establecer al conexion
            conectar(ligaDAO);
            
            //Testeando insertar un entrenador
            Entrenador ash = new Entrenador("Ash", "+3412345", "Hombre", 2, 2, "Eléctrico");
            insertarEntrenador(ligaDAO, ash);

            //Testeando insertar un entrenador repetido
            //El existeEntrenador nos comprueba esta fase
            insertarEntrenador(ligaDAO, ash);

            //Testeando borrar un entrenador
            //borrarEntrenador(ligaDAO, ash);

            //Testeando mostrar la lista de entrenadores
            Entrenador brock = new Entrenador("Brock", "+3412345", "Hombre",15 , 58, "Roca");
            insertarEntrenador(ligaDAO, brock);
            
            System.out.println("Testeando mostrar lista entrenadores");
            for (Entrenador e : selectAllEntrenadores(ligaDAO)) {
                System.out.println(e);
            }
            
            
            //Testeando insertar un pokemon, ash);
            Pokemon p = new Pokemon("Pikachu", "Eléctrico", 20, ash);
            insertarPokemon(ligaDAO,p);
            
            //Testeando insertar un pokemon con un entrenador que no existe
            
            
            //Testeando borrar un pokemon
            //Testeando buscar un entrenador por nombre
            //Testeando subir la fuerza de un pokemon
            p.setFuerza(p.getFuerza() + 1);
            subirFuerzaPokemon(ligaDAO, p);
            
            
            //Desconectar
            desconectar(ligaDAO);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void conectar(GestionDao ligaDAO) throws SQLException {
        System.out.println("Conectando...");
        ligaDAO.conectar();
    }

    public static void desconectar(GestionDao ligaDAO) throws SQLException {
        System.out.println("Desconectando...");
        ligaDAO.desconectar();
        System.out.println("Desconexión realizada.");
    }

    public static void insertarEntrenador(GestionDao ligaDao, Entrenador e) throws SQLException {

        try {
            ligaDao.insertarEntrenador(e);
            System.out.println("Entrenador insertado correctamente");
        } catch (ExcepcionLigaPokemon ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static void borrarEntrenador(GestionDao ligaDao, Entrenador e) throws SQLException {

        try {
            ligaDao.deleteEntrenador(e);
            System.out.println("Entrenador borrado correctamente");
        } catch (ExcepcionLigaPokemon ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static List<Entrenador> selectAllEntrenadores(GestionDao ligaDao) throws SQLException{
        List<Entrenador> entrenadores = new ArrayList();
        
        entrenadores = ligaDao.getAllEntrenadores();

        return entrenadores;   
    }
    
    public static void insertarPokemon(GestionDao ligaDao, Pokemon p) throws SQLException{
        
        try {
            ligaDao.altaPokemon(p);
            System.out.println("Pokemon:" + p.getNombre() + " dado de alta");
        } catch (ExcepcionLigaPokemon ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public static void subirFuerzaPokemon(GestionDao ligaDao, Pokemon p) throws SQLException{
        
        try {
            ligaDao.subirFuerzaPokemon(p);
            System.out.println("Pokemon:" + p.getNombre() + " subido con fuerza " + p.getFuerza());
        } catch (ExcepcionLigaPokemon ex) {
            System.out.println(ex.getMessage());
        }
        
    }

}
