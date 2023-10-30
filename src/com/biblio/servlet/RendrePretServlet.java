package com.biblio.servlet;

import com.biblio.beans.Livre;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LivreDAO;
import com.biblio.dao.PretDAO;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RendrePretServlet")
public class RendrePretServlet extends HttpServlet {

    public static final String IDPRET = "idPret";
    public static final String VUE = "/prets";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUERETARD = "/WEB-INF/retard.jsp";
    public static final String PRET = "pret";

    PretDAO pretDAO ;
    LivreDAO livreDAO;

    public void init(){
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPretDAO();
        this.livreDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String id = getValeurParametre(request ,  IDPRET);
       DateTime dateRendu = new DateTime();
       Pret pret;

       if (id != null){
           Long idPret = Long.parseLong(id);
           pret = pretDAO.search(idPret);
               if (pret.getDateRetour().isBeforeNow()){
                   request.setAttribute(PRET , pret);
                   this.getServletContext().getRequestDispatcher(VUERETARD).forward(request,response);
               }
               else
               {
                   try {
                       if (pret != null) {
                           pret.setRendu(true);
                           pret.setDateRetour(dateRendu);
                           Livre livre = livreDAO.search(pret.getLivre().getIdLivre());
                           if (livre != null){
                               livre.setDisponible(true);
                           }
                           pretDAO.update(pret);
                           livreDAO.updateDisponible(livre);
                       }
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                   response.sendRedirect(request.getContextPath() + VUE);
               }

       }
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
