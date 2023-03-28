/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Customer;
import entity.cequre.Role;
import entity.cequre.User;
import session.CustomerFacade;
import session.ProductFacade;

import session.RoleFacade;
import session.UserFacade;
import tools.PassEncrypt;


/**
 *
 * @author pupil
 */
@WebServlet(name = "LoginServlet", urlPatterns = {
    "/LoginServlet",
    "/login",
    "/enter",
    "/logout",
    "/registration",
    "/listProduct",
    "/newCustomer"})
public class LoginServlet extends HttpServlet {
    @EJB private CustomerFacade customerFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserFacade userFacade;
    @EJB private ProductFacade productFacade;
    private PassEncrypt pe = new PassEncrypt();
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
    public void init() throws ServletException {
        super.init();
        if(userFacade.count()>0) return;
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleFacade.create(role);
        User user = new User();
        user.setLogin("admin");
        String salt = pe.getSalt();
        String encryptPassword = pe.getEncryptedPass("admin", salt);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        Customer customer = new Customer();
        customer.setFirstname("Ivan");
        customer.setLastname("Ivanov");
        
        customerFacade.create(customer);
        user.setCustomer(customer);
        user.getRoles().add(role);
        
        role = new Role();
        role.setRoleName("EMPLOYEE");
        roleFacade.create(role);
        user.getRoles().add(role);
        
        role = new Role();
        role.setRoleName("USER");
        roleFacade.create(role);
        user.getRoles().add(role);
        userFacade.create(user);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/login":
                request.getRequestDispatcher("/WEB-INF/customers/login.jsp").forward(request, response);
                break;
            case "/enter":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/login").forward(request, response);
                    break;
                }
                String encryptPassword = pe.getEncryptedPass(password, user.getSalt());
                if(!encryptPassword.equals(user.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/login").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info", "Привет "+ user.getCustomer().getFirstname()+"!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                if(request.getSession(false) != null){
                    request.getSession(false).invalidate();
                }
                request.setAttribute("info", "Вы вышли");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/newCustomer":
                request.getRequestDispatcher("/WEB-INF/customers/addCustomer.jsp").forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                
                String cash = request.getParameter("cash");
                login = request.getParameter("login");
                password = request.getParameter("password");
                
                Customer customer = new Customer();
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                
                customer.setCash(Integer.parseInt(cash));
                customerFacade.create(customer);
                user = new User();
                user.setLogin(login);
                //Шифрование пароля
                String salt = pe.getSalt();
                encryptPassword = pe.getEncryptedPass(password, salt);
                //---
                user.setPassword(encryptPassword);
                user.setSalt(salt);
                user.setCustomer(customer);
                Role roleUser = roleFacade.findRoleByName("USER");
                user.getRoles().add(roleUser);
                userFacade.create(user);
                //request.setAttribute("listReaders", readerFacade.findAll());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProduct":
                request.setAttribute("listProduct", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/products/listProduct.jsp").forward(request, response);
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
