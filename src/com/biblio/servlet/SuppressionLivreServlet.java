package com.biblio.servlet;

import com.biblio.beans.Livre;
import com.biblio.dao.DAOException;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LivreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SuppressionLivreServlet")
public class SuppressionLivreServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String PARAM_ID_LIVRE = "idLivre";
    public  static final String VUE = "/livre";

    private LivreDAO livreDAO ;

    public void init(){
        this.livreDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idLivre = getValeurParametre(request , PARAM_ID_LIVRE);
        Long id = Long.parseLong(idLivre);
        if (id != null){
            try {
                Livre livre = livreDAO.search(id);
                livreDAO.delete(livre);
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
