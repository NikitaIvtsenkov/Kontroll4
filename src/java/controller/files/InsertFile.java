package controller.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;


@WebServlet(name = "InsertFile", urlPatterns = {
    "/insertFile/*"
})
public class InsertFile extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String filePath = request.getPathInfo();
        if(null == filePath){
            response.sendError((HttpServletResponse.SC_NOT_FOUND));
            return;
        }
        File file = new File(URLDecoder.decode(filePath, "UTF-8"));
        if(!file.exists()){
            response.sendError((HttpServletResponse.SC_NOT_FOUND));
            return;
        }
        String contentType = getServletContext().getMimeType(file.getName());
        if(null == contentType){
            contentType = "application/octet-stream";
        }
        response.reset();
        response.setContentType(contentType);
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader(
                "Content-Disposition","attachment: filensme=\"" 
                        + file.getName()
                        +"\""
        );
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file),DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(),DEFAULT_BUFFER_SIZE);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while((length = input.read(buffer))>0){
                output.write(buffer,0,length);
            }
        } finally {
            if(output != null){
                output.close();
            }
            if(input != null){
                input.close();
            }
        }
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
        processRequest(request, response);
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