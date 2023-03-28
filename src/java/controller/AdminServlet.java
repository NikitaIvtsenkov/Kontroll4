/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.cequre.Role;
import entity.cequre.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import session.CoverFacade;
import session.PurchaseFacade;

import session.RoleFacade;
import session.UserFacade;

/**
 *
 * 
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/changeRole",
    "/updateRole",
    "/review",
    "/showReview"
})
public class AdminServlet extends HttpServlet {

    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private PurchaseFacade purchaseFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
        Role roleAdmin = roleFacade.findRoleByName("ADMIN");
        if(!authUser.getRoles().contains(roleAdmin)){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            
            case "/changeRole":
                request.setAttribute("listRoles", roleFacade.findAll());
                request.setAttribute("listUsers", userFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/admin/changeRole.jsp").forward(request, response);
                break;
            case "/updateRole":
                String userId = request.getParameter("userId");
                String roleId = request.getParameter("roleId");
                User user = userFacade.find(Long.parseLong(userId));
                Role role = roleFacade.find(Long.parseLong(roleId));
                String action = request.getParameter("action");
                if("admin".equals(user.getLogin())){
                    request.setAttribute("info", "Редактирование прав невозможно");
                    request.getRequestDispatcher("/changeRole").forward(request, response);
                    break;
                }
                if(action.equals("Add")){
                    user.getRoles().add(role);
                }else if(action.equals("Delete")){
                    user.getRoles().remove(role);
                }
                userFacade.edit(user);
                /*session.setAttribute("user", user);*/
                request.getRequestDispatcher("/changeRole").forward(request, response);
                break;
                             case "/review":
                SimpleDateFormat sdt = new SimpleDateFormat("y");
                Integer year = Integer.parseInt(sdt.format(new Date()));
                List<Integer> years = new ArrayList<>();
                years.add(year - 1);
                years.add(year);
                request.setAttribute("years", years);
                request.getRequestDispatcher("/WEB-INF/admin/review.jsp").forward(request, response);
                break;   
             case "/showReview":
                String yearStr = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                if((month == null || month.isEmpty()) && (day == null || day.isEmpty())){
                    request.setAttribute("period", yearStr+" год");
                }else if(day == null || day.isEmpty() && (month != null || !month.isEmpty())){
                    request.setAttribute("period", month+" месяц");
                }else{
                    request.setAttribute("period",yearStr +" год, "+ month+" месяц, "+day+" день");
                }
                Map<Product,Integer> mapBooksRange = purchaseFacade.getTakedBooksInPeriod(yearStr,month,day);
                if(mapBooksRange.isEmpty()){
                    request.setAttribute("info", "В этот период книги не выдавались");
                }else{
                    request.setAttribute("mapBooksRange", mapBooksRange);
                }
                request.getRequestDispatcher("/WEB-INF/admin/showReview.jsp").forward(request, response);
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
