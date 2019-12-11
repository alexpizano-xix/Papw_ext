/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
public class search extends HttpServlet {

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
        String text = request.getParameter("txt");
        String opc = request.getParameter("opcion");
        
        HttpSession session = request.getSession(true);
        session.setAttribute("sWord", text);
        
        if(opc.equals("1")){
            List<product> products = product.getByTextName(text);

            request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        }
        
        if(opc.equals("2")){
            List<product> products = product.getByTextDesc(text);

            request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        }
        
        if(opc.equals("3")){
            List<product> products = product.getByText3D(text);

            request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        }
        
        if(opc.equals("4")){
            List<product> products = product.getByTextCD(text);

            request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        }
        
        if(opc.equals("5")){
            List<product> products = product.getByTextPhoto(text);

            request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        }
        
        request.getRequestDispatcher("searcher.jsp").forward(request, response);
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
        String text = request.getParameter("seeker");

        HttpSession session = request.getSession(true);
        session.setAttribute("sWord", text);
        
        List<product> products = product.getByText(text);

        request.setAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT, products);
        
        request.getRequestDispatcher("searcher.jsp").forward(request, response);
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
