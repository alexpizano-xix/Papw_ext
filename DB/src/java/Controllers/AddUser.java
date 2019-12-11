/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Utils.FilesUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author magoc
 */
@MultipartConfig(maxFileSize = 1000*1000*5, maxRequestSize = 1000*1000*25, fileSizeThreshold = 1000*1000)
public class AddUser extends HttpServlet {

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
     

        //Obtenemos los pArametros
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String username = request.getParameter("username");
        Part file = request.getPart("photo");
        
        HttpSession session = request.getSession(true);
            session.setAttribute("usn", username);
            session.setAttribute("psw", pass);
        
        //Creamos un folder para resguardar la imagen si es que no existe
        String path = request.getServletContext().getRealPath("");
        File fileSaveDir = new File(path + FilesUtils.RUTE_USER_IMAGE);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdir();
        }
        
        //Resguardamos la imagen
        String contentType = file.getContentType();//Resguarden esto para saber el tipo
        String nameImage =  file.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType);
        file.write(path + nameImage);
        
        //Agregamos a la base de datos
        User.AddUser(firstName, lastName, email, pass, username, file.getInputStream());
        //Redirecciona a la pagina que gustemos
        RequestDispatcher rd = request.getRequestDispatcher("showProductsProfile");
        rd.forward(request, response);
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
