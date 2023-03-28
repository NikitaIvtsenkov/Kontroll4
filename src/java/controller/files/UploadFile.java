package controller.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import entity.Cover;
import java.util.ArrayList;
import session.CoverFacade;


@WebServlet(name = "UploadFile", urlPatterns = {
    "/addCover",
    "/uploadCover",
    
})
@MultipartConfig
public class UploadFile extends HttpServlet {
    
    @EJB CoverFacade coverFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uploadFolder = "D:\\UploadDir\\webshop";
        String path = request.getServletPath();
        switch (path) {
            case "/addCover":
                request.getRequestDispatcher("/WEB-INF/products/addCover.jsp").forward(request, response);
                break;
            case "/uploadCover":
                List<Part> fileParts = request.getParts().stream().filter(
                        part -> "file".equals(part.getName()))
                        .collect(Collectors.toList());
                StringBuilder sb = new StringBuilder();
                for (Part filePart : fileParts) {
                    sb.append(uploadFolder + File.separator + getFileName(filePart));
                    File file = new File(sb.toString());
                    file.mkdirs();
                    try(InputStream fileContent = filePart.getInputStream()){
                        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    Cover cover = new Cover();
                    cover.setDescription(request.getParameter("description"));
                    cover.setUrl(sb.toString());
                    coverFacade.create(cover);
                    List<Cover>listCovers = new ArrayList<>();
                    listCovers.add(cover);  
                    request.setAttribute("listCovers", coverFacade.findAll());
                }
                request.setAttribute("info", "Файлы успешно загруженны");
                request.getRequestDispatcher("/WEB-INF/products/createProduct.jsp").forward(request, response);
                break;
            
        }
    }
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content
                        .substring(content.indexOf('=')+1)
                        .trim()
                        .replace("\"",""); 
            }
        }
        return null;
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