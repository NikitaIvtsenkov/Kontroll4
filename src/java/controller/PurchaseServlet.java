/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Product;
import entity.Purchase;

import entity.cequre.Role;
import entity.cequre.User;
import session.CustomerFacade;
import session.ProductFacade;
import session.PurchaseFacade;

import session.RoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {
    "/PurchaseServlet",
    "/goPurchase",
    "/purchase"
})
public class PurchaseServlet extends HttpServlet {
    @EJB private CustomerFacade customerFacade;
    @EJB private ProductFacade productFacade;
    @EJB private PurchaseFacade purchaseFacade;
    @EJB private RoleFacade roleFacade;
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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("user");
        if(authUser==null){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Role roleUser = roleFacade.findRoleByName("USER");
        if(!authUser.getRoles().contains(roleUser)){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
         String path = request.getServletPath();
        switch (path) {
            case "/goPurchase":

                request.setAttribute("listProduct", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/customers/goPurchase.jsp").forward(request, response);
                break;
            case "/purchase":
         String productId = request.getParameter("productId");
                String quantity = request.getParameter("quantity");
                Product product = productFacade.find(Long.parseLong(productId));
                User user = (User) session.getAttribute("user");
                Customer customer = customerFacade.find(user.getCustomer().getId());
                
                Purchase purchase = new Purchase();
                purchase.setProduct(product);
                purchase.setCustomer(user.getCustomer());
                
                purchase.setDate(new GregorianCalendar().getTime());
                purchase.setQuantity(Integer.parseInt(quantity));
                purchase.setHistory((product.getPrice())*Integer.parseInt(quantity));
                customer.setCash(customer.getCash()-(product.getPrice()*Integer.parseInt(quantity)));
                product.setQuantity(product.getQuantity()-Integer.parseInt(quantity));
                customerFacade.edit(customer);
                productFacade.edit(product);
                purchaseFacade.create(purchase);
                request.setAttribute("info", 
                        "продукт \""+product.getName()
                                +"\"Куплен покуптелем "
                                +user.getCustomer().getFirstname()+" "+user.getCustomer().getLastname()
                );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
