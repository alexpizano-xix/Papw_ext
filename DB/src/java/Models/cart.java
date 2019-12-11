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
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

/**
 *
 * @author alexp
 */
public class cart {

    private int idCart;
    private int idProduct;
    private int idUser;
    private int units;
    private String status;
    private String verified;
    private int price;

    /**
     * @return the idCart
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * @param idCart the idCart to set
     */
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    /**
     * @return the idProduct
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

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
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the verified
     */
    public String getVerified() {
        return verified;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(String verified) {
        this.verified = verified;
    }

    public cart() {

    }

    public cart(int id, int idp, int idus, int units, String status, String verified, int price) {
        this.idCart = id;
        this.idProduct = idp;
        this.idUser = idus;
        this.units = units;
        this.status = status;
        this.verified = verified;
        this.price = price;
    }

    static public List<cart> getAllCart(int idu) {
        List<cart> carts = new ArrayList<cart>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            //Esto dependera del nombre de su conexion recuerden
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            //Creamos el llamado
            statement = con.prepareCall(DBUtils.P_GET_CART);
            statement.setInt(1, idu);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idcart");
                int idp = rS.getInt("idProduct");
                int idus = rS.getInt("idUser");
                int units = rS.getInt("units");
                String status = rS.getString("status");
                String verified = rS.getString("verified");
                int price = rS.getInt("price");
                cart carro = new cart(id, idp, idus, units, status, verified, price);
                carts.add(carro);
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            //Si se finalizo bien cerramos todo
            try {
                if (rS != null) {
                    rS.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return carts;

    }
    
    static public List<cart> getAllCart2() {
        List<cart> carts = new ArrayList<cart>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            //Esto dependera del nombre de su conexion recuerden
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            //Creamos el llamado
            statement = con.prepareCall(DBUtils.P_GET_ALL_CART);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idcart");
                int idp = rS.getInt("idProduct");
                int idus = rS.getInt("idUser");
                int units = rS.getInt("units");
                String status = rS.getString("status");
                String verified = rS.getString("verified");
                int price = rS.getInt("price");
                cart carro = new cart(id, idp, idus, units, status, verified, price);
                carts.add(carro);
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            //Si se finalizo bien cerramos todo
            try {
                if (rS != null) {
                    rS.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return carts;

    }
    
    static public void changePrice(int price, int idC){
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
          
           statement = con.prepareCall(DBUtils.P_SET_PRICE);
            statement.setInt(1, price);
            statement.setInt(2, idC);
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
    
    static public void changeStatus(int idUn){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_STATUS);
            statement.setInt(1, idUn);
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
    
    static public void changeStatus2(int idUn){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_STATUS2);
            statement.setInt(1, idUn);
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

    static public void changeStatus3(int idUn){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_STATUS3);
            statement.setInt(1, idUn);
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
    static public void changeStatus4(int idUn){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_STATUS4);
            statement.setInt(1, idUn);
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
    
    static public void changeUnits(int idp){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_UNITS);
            statement.setInt(1, idp);
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
    
    static public void changeUnits2(int idp){
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
          
           statement = con.prepareCall(DBUtils.P_CHANGE_UNITS2);
            statement.setInt(1, idp);
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
    
    static public void deleteCart(int idu){
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
          
           statement = con.prepareCall(DBUtils.P_DELETE_CART);
            statement.setInt(1, idu);
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
    
    static public void deleteCart2(int idu){
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
          
           statement = con.prepareCall(DBUtils.P_DELETE_CART2);
            statement.setInt(1, idu);
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
    
    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
