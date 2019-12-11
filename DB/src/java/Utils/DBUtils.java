/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author magoc
 */
public class DBUtils {
    public static String P_ADD_USER = "CALL addUser(?,?,?,?,?,?);";
    public static String P_IMAGE_USER = "CALL GetImageUser(?);";
    public static String P_SEARCH_USER = "CALL searchUser(?,?);";
    public static String P_SEARCH_USERNAME = "CALL searchUsername(?);";
    public static String P_GETIMAGEUSER = "call getImageUser(?);";
    public static String P_SEARCH_ALL_USERS = "call searchAllUsers();";
    
    public static String P_ADD_PRODUCT = "CALL addProduct(?,?,?,?,?,?,?,?);";
    public static String P_GETALL_PRODUCT = "CALL getAllProduct();";
    public static String P_GETALL_PRODUCT_RATE = "CALL getAllProductRate();";
    public static String P_GETALL_PRODUCT_VIEWS = "CALL getAllProductViews();";
    public static String P_GET_PRODUCT = "CALL getProduct(?);";
    public static String P_GET3MODEL_PRODUCT = "CALL get3DModelProduct();";
    public static String P_GETPHOTOGRAPHY_PRODUCT = "CALL getPhotographyProduct();";
    public static String P_GETCHARACTERDESIGN_PRODUCT = "CALL getAllCharacterDesignProduct();";
    public static String P_CHANGEPUBLISH = "CALL changePublish(?);";
    public static String p_CHANGESAVE = "CALL changeSaved(?);";
    public static String P_DELETEPRODUCT = "CALL deleteProduct(?);";
    public static String P_GETIMAGEPRODUCT = "call getImageProduct(?);";
    public static String P_VIDEO_PRODUCT = "call getImageProduct(?);";
    public static String P_CHANGE_RATE = "call changeRate(?,?);";
    public static String P_CHANGE_RATE2 = "call changeRate2(?,?);";
    public static String P_CHANGE_RATE3 = "call changeRate3(?,?);";
    public static String P_CHANGE_RATE4 = "call changeRate4(?,?);";
    public static String P_CHANGE_RATE5 = "call changeRate5(?,?);";
    public static String P_CHANGE_VIEWS = "call changeViews(?);";
    public static String P_SEARCH_TEXT = "call searchText(?);";
    public static String P_SEARCH_TEXT2 = "call searchText2(?);";
    public static String P_SEARCH_TEXT3 = "call searchText3(?);";
    public static String P_SEARCH_TEXT4 = "call searchText4(?);";
    public static String P_SEARCH_TEXT5 = "call searchText5(?);";
    public static String P_SEARCH_TEXT6 = "call searchText6(?);";
    
    public static String P_ADD_TO_CART = "call addToCart(?,?);";
    public static String P_GET_CART = "call getCart(?);";
    public static String P_GET_ALL_CART = "call getAllCart();";
    public static String P_SET_PRICE = "call setPrice(?,?);";
    public static String P_CHANGE_STATUS = "call changeStatus(?);";
    public static String P_CHANGE_STATUS2 = "call changeStatus2(?);";
    public static String P_CHANGE_STATUS3 = "call changeStatus3(?);";
    public static String P_CHANGE_STATUS4 = "call changeStatus4(?);";
    public static String P_CHANGE_UNITS = "call changeUnits(?);";
    public static String P_CHANGE_UNITS2 = "call changeUnits2(?);";
    public static String P_DELETE_CART = "call deleteCart(?);";
    public static String P_DELETE_CART2 = "call deleteCart2(?);";
    
    public static String P_ADD_MESSAGE = "call addMessage(?,?,?,?);";
    public static String P_GET_ALL_MESSAGES = "call getAllMessages();";
    
    public static String P_ADD_COMMENT = "CALL addComment(?,?,?);";
    public static String P_SEARCH_ALL_COMMENTS = "call searchAllComments();";
}
