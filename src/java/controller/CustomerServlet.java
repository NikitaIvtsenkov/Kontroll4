/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import entity.cequre.User;
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
import javax.servlet.http.HttpSession;
import session.CustomerFacade;


/**
 *
 * @author pupil
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {
    "/addCustomer",
    "/doMoney",
    "/customerList",
    "/addMoney",
    "/customer"
    })
public class CustomerServlet extends HttpServlet {
@EJB private CustomerFacade customerFacade;
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
        String path = request.getServletPath();
        switch (path) {
             case "/customerList":
                request.setAttribute("listCustomers", customerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/customers/customerList.jsp").forward(request, response);
                break;    
            
            case "/addCustomer":
                List<Customer>listCustomer = new ArrayList<>();
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String cash = request.getParameter("cash");
                Customer customer = new Customer();
                
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setCash(Integer.parseInt(cash));
                
                listCustomer.add(customer);
                customerFacade.create(customer);
                request.getRequestDispatcher("/WEB-INF/customers/customerList.jsp").forward(request, response);
                case "/customer":
                    String id = request.getParameter("id");
                    request.setAttribute("customer", customerFacade.find(Long.parseLong(id)));
                    request.getRequestDispatcher("/WEB-INF/customers/customer.jsp").forward(request, response);
                break;
                case "/addMoney":
               
           
                request.getRequestDispatcher("/WEB-INF/customers/addMoney.jsp").forward(request, response);
                break;
                case "/doMoney":
//                     User user = (User) session.getAttribute("user");
                User   user = (User) session.getAttribute("user");
                String addmoney = request.getParameter("addMoney");  
                customer = customerFacade.find(user.getCustomer().getId());
                customer.setCash(customer.getCash() + Integer.parseInt(addmoney));
                //int sum = (Integer.parseInt(addmoney))+ (customer.getWallet());
                
                customerFacade.edit(customer);
               
                request.setAttribute("info", 
                        "Cash granted \""+user.getCustomer().getFirstname() + " " + customer.getCash()
                                
                );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
