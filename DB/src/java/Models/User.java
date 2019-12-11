/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.DBUtils;
import java.sql.Connection;
import com.mysql.jdbc.Statement;
import java.io.InputStream;
import static java.lang.Boolean.TRUE;
import static java.lang.System.console;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author magoc
 */
public class User {
    //Atributos encapsulados
    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String pass;
    private String username;
    private byte[] imageUser;
    private Boolean acceder;
  
    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return pass;
    }

    /**
     * @param password the password to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the imageUser
     */
    public byte[] getImageUser() {
        return imageUser;
    }

    /**
     * @param imageUser the imageUser to set
     */
    public void setImageUser(byte[] imageUser) {
        this.imageUser = imageUser;
    }
    
    
    //Constructores
    public User() {
    }
    
    public User(int id, String username){
        this.idUser = id;
        this.username = username;
    }
    
    public User(int idUser, String firstName, String email, String pass, String username, String correo, String direccion, String telefono, byte[] imageUser) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.email = email;
        this.pass = pass;
        this.username=username;
        this.imageUser=imageUser;
    }
    
    public User(String username, String fName, String lName, String email, int id){
        this.username = username;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.idUser = id;
    }
    
    //Agregar un usuario
    static public void AddUser(String firstName, String lastName, String email, String pass, String username, InputStream userImage){
      
        
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();
          
           statement = con.prepareCall(DBUtils.P_ADD_USER);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, pass);
            statement.setString(5, username);
            statement.setBinaryStream(6, userImage);
            rS = statement.executeQuery();

        }catch(SQLException ex){
            System.out.print("Entro al Serlvet de add user" + ex.getMessage());
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al in DataSource: " + ex.getMessage());
        } finally{
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
    }
    
    static public List<User> searchAllUsers(){
        List<User> usuarios = new ArrayList<User>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            //Esto dependera del nombre de su conexion recuerden
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            //Creamos el llamado
            statement = con.prepareCall(DBUtils.P_SEARCH_ALL_USERS);
            rS = statement.executeQuery();
            
            //Conseguimos los datos y los agregamos a una lista
            while(rS.next()){
                int id = rS.getInt("idUser");
                String un = rS.getString("username");
                User users = new User(id, un);
                usuarios.add(users);
            }
        }catch(SQLException ex){
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally{
            //Si se finalizo bien cerramos todo
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return usuarios;       
    }
    
    static public int searchUser(String username, String pass){
        int id1 =0;
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();
          
            statement = con.prepareCall(DBUtils.P_SEARCH_USER);
            statement.setString(1, username);
            statement.setString(2, pass);
            rS = statement.executeQuery();
            
            while(rS.next()){
                id1 = rS.getInt("idUser");
            }

        }catch(SQLException ex){
            System.out.print("Entro al Serlvet de add user" + ex.getMessage());
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al in DataSource: " + ex.getMessage());
        } finally{
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return id1;
    }
    
    static public List<User> searchUser2(String username, String pass){
        List<User> users = new ArrayList<User>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();
          
            statement = con.prepareCall(DBUtils.P_SEARCH_USER);
            statement.setString(1, username);
            statement.setString(2, pass);
            rS = statement.executeQuery();
            
            while(rS.next()){
                String usn = rS.getString("username");
                String fName = rS.getString("firstName");
                String lName = rS.getString("lastName");
                String email = rS.getString("email");
                int id = rS.getInt("iduser");
                
                User us = new User(usn, fName, lName, email, id);
                users.add(us);
            }

        }catch(SQLException ex){
            System.out.print("Entro al Serlvet de add user" + ex.getMessage());
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al in DataSource: " + ex.getMessage());
        } finally{
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return users;
    }
    
    static public byte[] getImageUser(int idUser){
        byte[] imageBytes = null;
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            statement = con.prepareCall(DBUtils.P_GETIMAGEUSER);
            statement.setInt(1, idUser);
            rS = statement.executeQuery();
            while(rS.next()){
                imageBytes = rS.getBytes("photo");
            }
        }catch(SQLException ex){
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally{
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return imageBytes;
    }

    /**
     * @return the acceder
     */
    public Boolean getAcceder() {
        return acceder;
    }

    /**
     * @param acceder the acceder to set
     */
    public void setAcceder(Boolean acceder) {
        this.acceder = acceder;
    }
}
