/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.product;
import Utils.FilesUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author oziel
 */
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class addProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String units = request.getParameter("units");
        Part image1 = request.getPart("image1");
        Part image2 = request.getPart("image2");
        Part image3 = request.getPart("image3");
        Part video = request.getPart("video");

        String path = request.getServletContext().getRealPath("");
        File fileSaveDir = new File(path + FilesUtils.RUTE_USER_IMAGE);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        //Resguardamos la imagen
        String contentType = image1.getContentType();//Resguarden esto para saber el tipo
        String nameImage = image1.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType);
        image1.write(path + nameImage);

        String contentType2 = image2.getContentType();//Resguarden esto para saber el tipo
        String nameImage2 = image2.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType2);
        image2.write(path + nameImage2);

        String contentType3 = image3.getContentType();//Resguarden esto para saber el tipo
        String nameImage3 = image3.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType3);
        image3.write(path + nameImage3);

        String contentType4 = video.getContentType();//Resguarden esto para saber el tipo
        String nameImage4 = video.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType4);
        video.write(path + nameImage4);

        product.addProduct(productName, description, category, units, image1.getInputStream(), image2.getInputStream(), image3.getInputStream(), video.getInputStream());
       
        RequestDispatcher rd = request.getRequestDispatcher("showProductsProfile");
        rd.forward(request, response);
        
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Product created successfuly');");
        out.println("</script>");
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
