/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.cart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexp
 */
public class changeStatusCart extends HttpServlet {

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
        String opc = request.getParameter("opcion");
        if (opc.equals("1")) {
            String id = request.getParameter("idUn");
            int id2 = Integer.parseInt(id.trim());

            cart.changeStatus(id2);
            request.getRequestDispatcher("openChat").forward(request, response);
        }
        if (opc.equals("2")) {
            String id = request.getParameter("idUn");
            int id2 = Integer.parseInt(id.trim());
            
            cart.changeStatus2(id2);
            request.getRequestDispatcher("openChat").forward(request, response);
        }
        if (opc.equals("3")) {
            String id = request.getParameter("idUn");
            int id2 = Integer.parseInt(id.trim());
            
            cart.changeStatus3(id2);
            request.getRequestDispatcher("openChat").forward(request, response);
        }
        if (opc.equals("4")) {
            String id = request.getParameter("idUn");
            int id2 = Integer.parseInt(id.trim());
            
            cart.changeStatus4(id2);
            request.getRequestDispatcher("showProducts").forward(request, response);
        }
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
