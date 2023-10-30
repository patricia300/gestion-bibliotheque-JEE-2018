package com.biblio.servlet;

import com.biblio.beans.Pret;
import com.biblio.dao.DAOException;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.PretDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SuppressionPretServlet")
public class SuppressionPretServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String PARAM_ID_Pret = "idPret";
    public  static final String VUE = "/prets";

    private PretDAO pretDAO;

    public void init(){
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPretDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = getValeurParametre(request , PARAM_ID_Pret);
        Long idLivre = Long.parseLong(id);
        if (idLivre != null){
            try {
                Pret pret = pretDAO.search(idLivre);
                pretDAO.delete(pret);
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
