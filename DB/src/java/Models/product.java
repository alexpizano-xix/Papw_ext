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
 * @author oziel
 */
public class product {

    private int idproduct;
    private String productName;
    private String description;
    private String category;
    private int units;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private byte[] video;
    private int price;
    private int views;
    private int soldOut;
    private float rate;
    private String status;

    /**
     * @return the idproduct
     */
    public int getIdproduct() {
        return idproduct;
    }

    /**
     * @param idproduct the idproduct to set
     */
    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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
     * @return the image1
     */
    public byte[] getImage1() {
        return image1;
    }

    /**
     * @param image1 the image1 to set
     */
    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    /**
     * @return the image2
     */
    public byte[] getImage2() {
        return image2;
    }

    /**
     * @param image2 the image2 to set
     */
    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    /**
     * @return the image3
     */
    public byte[] getImage3() {
        return image3;
    }

    /**
     * @param image3 the image3 to set
     */
    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    /**
     * @return the video
     */
    public byte[] getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(byte[] video) {
        this.video = video;
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

    /**
     * @return the views
     */
    public int getViews() {
        return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(int views) {
        this.views = views;
    }

    /**
     * @return the soldOut
     */
    public int getSoldOut() {
        return soldOut;
    }

    /**
     * @param soldOut the soldOut to set
     */
    public void setSoldOut(int soldOut) {
        this.soldOut = soldOut;
    }

    /**
     * @return the rate
     */
    public float getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(float rate) {
        this.rate = rate;
    }

    //Constructores
    public product() {
    }

    public product(int idproduct, String productName, String description, String category, int units, byte[] image1, byte[] image2, byte[] image3, byte[] video, int views, float rate, String status, int price) {
        this.idproduct = idproduct;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.units = units;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.video = video;
        this.status = status;
        this.rate = rate;
        this.views = views;
    }

    public product(String productName) {
        this.productName = productName;
    }

    static public void addProduct(String productName, String description, String category, String units, InputStream image1, InputStream image2, InputStream image3, InputStream video) {
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        int i = Integer.parseInt(units);
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            statement = con.prepareCall(DBUtils.P_ADD_PRODUCT);
            statement.setString(1, productName);
            statement.setString(2, description);
            statement.setString(3, category);
            statement.setInt(4, i);
            statement.setBinaryStream(5, image1);
            statement.setBinaryStream(6, image2);
            statement.setBinaryStream(7, image3);
            statement.setBinaryStream(8, video);
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

    static public void addToCart(int idu, int idp) {
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

            statement = con.prepareCall(DBUtils.P_ADD_TO_CART);
            statement.setInt(1, idu);
            statement.setInt(2, idp);
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

    static public void changePublish(String productName) {
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

            statement = con.prepareCall(DBUtils.P_CHANGEPUBLISH);
            statement.setString(1, productName);
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

    static public void changeSave(String productName) {
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

            statement = con.prepareCall(DBUtils.p_CHANGESAVE);
            statement.setString(1, productName);
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
    
    static public void changeViews(int idp) {
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

            statement = con.prepareCall(DBUtils.P_CHANGE_VIEWS);
            statement.setInt(1, idp);
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

    static public void deleteProduct(String productName) {
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

            statement = con.prepareCall(DBUtils.P_DELETEPRODUCT);
            statement.setString(1, productName);
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

    static public byte[] GetImageProduct(int idproduct) {
        byte[] imageBytes = null;
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

            statement = con.prepareCall(DBUtils.P_GETIMAGEPRODUCT);
            statement.setInt(1, idproduct);
            rS = statement.executeQuery();
            while (rS.next()) {
                imageBytes = rS.getBytes("image1");
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
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
        return imageBytes;
    }

    static public byte[] GetImageProduct2(int idproduct) {
        byte[] imageBytes = null;
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

            statement = con.prepareCall(DBUtils.P_GETIMAGEPRODUCT);
            statement.setInt(1, idproduct);
            rS = statement.executeQuery();
            while (rS.next()) {
                imageBytes = rS.getBytes("image2");
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
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
        return imageBytes;
    }

    static public byte[] GetImageProduct3(int idproduct) {
        byte[] imageBytes = null;
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

            statement = con.prepareCall(DBUtils.P_GETIMAGEPRODUCT);
            statement.setInt(1, idproduct);
            rS = statement.executeQuery();
            while (rS.next()) {
                imageBytes = rS.getBytes("image3");
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
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
        return imageBytes;
    }
    
    static public byte[] GetVideoProduct(int idProduct){
        byte[] videoBytes = null;
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

            statement = con.prepareCall(DBUtils.P_VIDEO_PRODUCT);
            statement.setInt(1, idProduct);
            rS = statement.executeQuery();
            while(rS.next()){
                videoBytes = rS.getBytes("video");
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
        return videoBytes;
    }

    static public List<product> getAllProduct() {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_GETALL_PRODUCT);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getAllProductRate() {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_GETALL_PRODUCT_RATE);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByText(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByTextName(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT2);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByTextDesc(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT3);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByText3D(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT4);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByTextCD(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT5);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getByTextPhoto(String txt) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_SEARCH_TEXT6);
            statement.setString(1, txt);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }

    static public List<product> getAllProductViews() {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_GETALL_PRODUCT_VIEWS);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }
    
    static public List<product> getProduct(int id) {
        List<product> products = new ArrayList<product>();
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
            statement = con.prepareCall(DBUtils.P_GET_PRODUCT);
            statement.setInt(1, id);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                int id2 = rS.getInt("idproduct");
                String name = rS.getString("productName");
                String description = rS.getString("description");
                String category = rS.getString("category");
                int units = rS.getInt("units");
                byte[] image1 = rS.getBytes("image1");
                byte[] image2 = rS.getBytes("image2");
                byte[] image3 = rS.getBytes("image3");
                byte[] video = rS.getBytes("video");
                int views = rS.getInt("views");
                float rate = rS.getFloat("rate");
                String statu = rS.getString("status");
                int price = rS.getInt("price");
                product product = new product(id2, name, description, category, units, image1, image2, image3, video, views, rate, statu, price);
                products.add(product);
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
        return products;
    }

    static public void changeRate(String opc, int idp) {
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

            if (opc.equals("1")) {
                statement = con.prepareCall(DBUtils.P_CHANGE_RATE);
                statement.setString(1, opc);
                statement.setInt(2, idp);
                rS = statement.executeQuery();
            }
            if (opc.equals("2")) {
                statement = con.prepareCall(DBUtils.P_CHANGE_RATE2);
                statement.setString(1, opc);
                statement.setInt(2, idp);
                rS = statement.executeQuery();
            }
            if (opc.equals("3")) {
                statement = con.prepareCall(DBUtils.P_CHANGE_RATE3);
                statement.setString(1, opc);
                statement.setInt(2, idp);
                rS = statement.executeQuery();
            }
            if (opc.equals("4")) {
                statement = con.prepareCall(DBUtils.P_CHANGE_RATE4);
                statement.setString(1, opc);
                statement.setInt(2, idp);
                rS = statement.executeQuery();
            }
            if (opc.equals("5")) {
                statement = con.prepareCall(DBUtils.P_CHANGE_RATE5);
                statement.setString(1, opc);
                statement.setInt(2, idp);
                rS = statement.executeQuery();
            }

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

}
