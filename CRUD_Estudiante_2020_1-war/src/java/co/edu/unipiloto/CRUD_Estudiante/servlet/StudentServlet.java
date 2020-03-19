/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.CRUD_Estudiante.servlet;

import co.edu.unipiloto.CRUD_Estudiante.entity.Estudiantes;
import co.edu.unipiloto.CRUD_Estudiante.session.EstudiantesFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DIEGO
 */
public class StudentServlet extends HttpServlet {
    
    @EJB
    private EstudiantesFacadeLocal estudiantesFacade;
    

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
        try (PrintWriter out = response.getWriter()) {
            String studentIdStr= request.getParameter("studentId");
            int studentId=0;
            //if (studentIdStr==null && !studentIdStr.equals(""))
                studentId=Integer.parseInt(studentIdStr);
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int yearLevel = Integer.parseInt(request.getParameter("yearLevel"));
            String action = request.getParameter("action");
            System.out.println(studentId + "-" + action);
            Estudiantes est = new Estudiantes(studentId, firstName, lastName, yearLevel);
                        
            if (action.equals("Add")) {
                System.out.println("adicionar"+est.toString());
                estudiantesFacade.create(est);
            } else {
                if (action.equalsIgnoreCase("Edit")) {
                    estudiantesFacade.edit(est);
                } else {
                    if (action.equalsIgnoreCase("Delete")) {
                        estudiantesFacade.remove(est);
                    }
                    else {
                        System.out.println("buscar"+est.toString());
                        est=estudiantesFacade.find(est);
                    }
                } 
            }
            request.setAttribute("student", est);
            request.setAttribute("allStudents", estudiantesFacade.findAll());
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
