<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-18
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="link_css.jsp"/>
</head>
<body>
<c:import url="menu.jsp"/>

 <div class="container">
     <!--Section: Contact v.2-->
     <section class="mb-4">

         <!--Section heading-->
         <h2 class="h1-responsive font-weight-bold text-center my-4">LISTE DES PRETS</h2>
         <!--Section description-->

         <div class="row">
             <!--Grid column-->
             <div class="col-md-9 mb-md-0 mb-5">
                 <form>
                     <!--Grid row-->
                     <div class="row">
                         <!--Grid column-->
                         <div class="col-md-4">
                             <div class="md-form mb-0">
                                 <input readonly type="text" class="form-control text-uppercase" value="N°Lecteur : LT0${lecteur.idLecteur}">
                             </div>
                         </div>
                         <!--Grid column-->
                         <!--Grid column-->
                         <div class="col-md-6">
                             <div class="md-form mb-0">
                                 <input readonly type="text"  class="form-control text-uppercase" value="Nom : ${lecteur.nomLecteur}">
                             </div>
                         </div>
                         <!--Grid column-->
                         <!--Grid column-->
                         <div class="col-md-10">
                             <div class="md-form mb-0">
                                 <input readonly type="text" value="Prenom : ${lecteur.prenomLecteur}" class="form-control text-uppercase">
                             </div>
                         </div>
                         <!--Grid column-->
                     </div>
                     <!--Grid row-->
                 </form>
             </div>
             <!--Grid column-->
         </div>
     </section>
     <!--Section: Contact v.2-->
     <c:choose>
         <c:when test="${empty prets}">
             <p class="alert alert-info">Ce lecteur n'a fait aucun pret</p>
         </c:when>
         <c:otherwise>
             <!-- Tableau qui liste les ouvrages -->
             <!-- Table with panel -->
             <div class="card card-cascade narrower">
                 <!--Card image-->
                 <div class="view view-cascade gradient-card-header primary-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
                     <a href="" class="white-text mx-3">LISTE DES PRETS NON RENDU</a>
                 </div>
                 <!--/Card image-->
                 <div class="px-4">
                     <div class="table-wrapper">
                         <!--Table-->
                         <table class="table table-hover mb-0">
                             <!--Table head-->
                             <thead>
                             <tr>
                                 <th class="th-lg">
                                     <a>DESIGNATION
                                         <i class="fas fa-sort ml-1"></i>
                                     </a>
                                 </th>
                                 <th class="th-lg">
                                     <a href="">AUTEUR
                                         <i class="fas fa-sort ml-1"></i>
                                     </a>
                                 </th>
                                 <th class="th-lg">
                                     <a href="">DATE EDITION
                                         <i class="fas fa-sort ml-1"></i>
                                     </a>
                                 </th>
                                 <th class="th-lg">
                                     <a href="">DATE PRET
                                         <i class="fas fa-sort ml-1"></i>
                                     </a>
                                 </th>
                             </tr>
                             </thead>
                             <!--Table head-->
                             <!--Table body-->
                             <tbody>
                             <c:forEach items="${prets}" var="mapPret" varStatus="nombre">
                                 <c:if test="${mapPret.rendu == false}">
                                     <tr>
                                         <td><c:out value="${mapPret.livre.designation}"/></td>
                                         <td class="text-uppercase"><c:out value="${mapPret.livre.auteur}"/></td>
                                         <td><c:out value="${mapPret.livre.dateEdition}"/></td>
                                         <td><joda:format value="${mapPret.datePret}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                     </tr>
                                 </c:if>
                             </c:forEach>
                             </tbody>
                             <!--Table body-->
                         </table>
                         <!--Table-->
                     </div>
                 </div>
             </div>
             <!-- Table with panel -->
             <!-- Fin Tableau qui liste les ouvrages -->
             <p class="alert alert-light pt-1">Nombre de livres pretés : ${nbNonRendu}</p>
         </c:otherwise>
     </c:choose>
 </div>
<c:import url="footer.jsp"/>
</body>
<c:import url="script.jsp"/>
</html>
