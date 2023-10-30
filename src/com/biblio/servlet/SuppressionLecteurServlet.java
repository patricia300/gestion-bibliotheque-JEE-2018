package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.dao.DAOException;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SuppressionLecteurServlet")
public class SuppressionLecteurServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String PARAM_ID_LECTEUR = "idLecteur";
    public  static final String VUE = "/lecteur";

    private LecteurDAO lecteurDAO;

    public void init(){
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idLecteur = getValeurParametre(request,PARAM_ID_LECTEUR);
        Long id = Long.parseLong(idLecteur);

        if (id != null){
            try {
                Lecteur lecteur = lecteurDAO.search(id);
                lecteurDAO.delete(lecteur);
            }catch (DAOException e){
                e.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath() + VUE);

    }
    private static String getValeurParametre(HttpServletRequest req , String nomChamp){
            String champ = req.getParameter(nomChamp);
            if (champ == null || champ.trim().length() ==0){
                return null;
            }else {
                return champ;
            }
    }
}

