/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Models.cart;
import Models.product;
import Utils.RequestUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oziel
 */
public class showProducts extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);
        String username = session.getAttribute("usn").toString();
        String pass2 = session.getAttribute("psw").toString();
        
        List<product> products = product.getAllProduct();
        List<User> users = User.searchUser2(username, pass2);
        List<product> rateProducts = product.getAllProductRate();
        List<product> viewsProducts = product.getAllProductViews();
        
        
        //Agregamos los usuarios obtenidos a nuestro request
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS, products);
        request.setAttribute(RequestUtils.KEY_USER, users);
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_RATE, rateProducts);
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_VIEWS, viewsProducts);
        //Mandamos nuestro Request al JSP llamado showUsers
        //response.sendRedirect("showUsers.jsp");
        request.getRequestDispatcher("index2.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        session.setAttribute("id", 0);
        session.setAttribute("username", 0);
        String username = session.getAttribute("usn").toString();
        String pass2 = session.getAttribute("psw").toString();
        int id = User.searchUser(username, pass2);
        
        List<cart> carts = cart.getAllCart(id);
        List<User> users = User.searchUser2(username, pass2);
        List<product> products = product.getAllProduct();
        List<product> rateProducts = product.getAllProductRate();
        List<product> viewsProducts = product.getAllProductViews();
        
        request.setAttribute(RequestUtils.KEY_CART, carts);
        request.setAttribute(RequestUtils.KEY_USER, users);
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS, products);
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_RATE, rateProducts);
        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_VIEWS, viewsProducts);
        
        request.getRequestDispatcher("index2.jsp").forward(request, response);
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
