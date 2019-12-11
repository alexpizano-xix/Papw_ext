/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.DBUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alexp
 */
public class messages {

    private int idMessages;
    private int idAdmi;
    private int idUser;
    private String messages;
    private int idSender;

    /**
     * @return the idMessages
     */
    public int getIdMessages() {
        return idMessages;
    }

    /**
     * @param idMessages the idMessages to set
     */
    public void setIdMessages(int idMessages) {
        this.idMessages = idMessages;
    }

    /**
     * @return the idAdmi
     */
    public int getIdAdmi() {
        return idAdmi;
    }

    /**
     * @param idAdmi the idAdmi to set
     */
    public void setIdAdmi(int idAdmi) {
        this.idAdmi = idAdmi;
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
     * @return the messages
     */
    public String getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(String messages) {
        this.messages = messages;
    }
    
    public messages(int id, int idAdmi, int idUser, String message, int sender){
        this.idMessages = id;
        this.idAdmi = idAdmi;
        this.idUser = idUser;
        this.messages = message;
        this.idSender = sender;
    }

    static public void addMessage(int idAdmi2, int idUn2, String message, int idSend) {
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            statement = con.prepareCall(DBUtils.P_ADD_MESSAGE);
            statement.setInt(1, idAdmi2);
            statement.setInt(2, idUn2);
            statement.setString(3, message);
            statement.setInt(4, idSend);
            rS = statement.executeQuery();

        } catch (SQLException ex) {
            System.out.print("Entro al Serlvet de add user" + ex.getMessage());
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al in DataSource: " + ex.getMessage());
        } finally {
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
    }

    static public List<messages> getAllMessages() {
        List<messages> msg = new ArrayList<messages>();
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
            statement = con.prepareCall(DBUtils.P_GET_ALL_MESSAGES);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idmessages");
                int idAdmi = rS.getInt("idAdmi");
                int idUn = rS.getInt("idUser");
                String message = rS.getString("message");
                int idSender = rS.getInt("sender");
                messages allMsg = new messages(id, idAdmi, idUn, message, idSender);
                msg.add(allMsg);
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
        return msg;
    }

    /**
     * @return the idSender
     */
    public int getIdSender() {
        return idSender;
    }

    /**
     * @param idSender the idSender to set
     */
    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

}
