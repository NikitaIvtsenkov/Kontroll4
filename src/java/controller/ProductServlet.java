package controller;

import entity.Cover;
import entity.Product;
import static entity.Purchase_.product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CoverFacade;
import session.ProductFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "ProductServlet", urlPatterns = {
    "/ProductServlet", 
    
    
    "/createProduct",
    "/newProduct",
    "/product"
    })
public class ProductServlet extends HttpServlet {
@EJB private ProductFacade productFacade;
@EJB private CoverFacade coverFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        switch (path) {
            
                
                case "/newProduct":
//                List<Author>listAuthors = new ArrayList<>();
              
               
                
                request.getRequestDispatcher("/WEB-INF/products/createProduct.jsp").forward(request, response);
                break;
                case "/createProduct":
                
                List<Product>listProduct = new ArrayList<>();
                
                String name = request.getParameter("name");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                Product product = new Product();
                String coverId = request.getParameter("coverId");
                Cover cover = coverFacade.find(Long.parseLong(coverId));
                product.setCover(cover);
                product.setName(name);
                product.setQuantity(Integer.parseInt(quantity));
                product.setPrice(Integer.parseInt(price));                
                             
                listProduct.add(product);
                
                productFacade.create(product);
                request.setAttribute("listProduct", productFacade.findAll());
                
                request.getRequestDispatcher("/listProduct").forward(request, response);
                case "/product":
                    String id = request.getParameter("id");
                    request.setAttribute("product", productFacade.find(Long.parseLong(id)));
//                    request.setAttribute("listCovers",coverFacade.find(Long.parseLong(id)));
                    request.getRequestDispatcher("/WEB-INF/products/product.jsp").forward(request, response);
                break; 
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
