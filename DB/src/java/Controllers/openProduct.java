/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Models.cart;
import Models.comments;
import Models.product;
import Utils.RequestUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexp
 */
public class openProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("idP"));
        HttpSession session = request.getSession(true);
        String username = session.getAttribute("usn").toString();
        String pass2 = session.getAttribute("psw").toString();
        int id2 = User.searchUser(username, pass2);
        
        List<cart> carts = cart.getAllCart(id2);
        List<product> products = product.getProduct(id);
        List<User> users = User.searchUser2(username, pass2);
        List<comments> comm = comments.searchAllComments();
        List<User> allUsers = User.searchAllUsers();
        
        request.setAttribute(RequestUtils.KEY_PRODUCT, products);
        request.setAttribute(RequestUtils.KEY_USER, users);
        request.setAttribute(RequestUtils.KEY_CART, carts);
        request.setAttribute(RequestUtils.KEY_ALL_COMMENTS, comm);
        request.setAttribute(RequestUtils.KEY_ALL_USERS, allUsers);
        
        
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("idProduct"));
        HttpSession session = request.getSession(true);
        String username = session.getAttribute("usn").toString();
        String pass2 = session.getAttribute("psw").toString();
        int id2 = User.searchUser(username, pass2);
        
        List<cart> carts = cart.getAllCart(id2);
        List<product> products = product.getProduct(id);
        List<User> users = User.searchUser2(username, pass2);
        List<comments> comm = comments.searchAllComments();
        List<User> allUsers = User.searchAllUsers();
        product.changeViews(id);
        
        request.setAttribute(RequestUtils.KEY_PRODUCT, products);
        request.setAttribute(RequestUtils.KEY_USER, users);
        request.setAttribute(RequestUtils.KEY_CART, carts);
        request.setAttribute(RequestUtils.KEY_ALL_COMMENTS, comm);
        request.setAttribute(RequestUtils.KEY_ALL_USERS, allUsers);
        
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
