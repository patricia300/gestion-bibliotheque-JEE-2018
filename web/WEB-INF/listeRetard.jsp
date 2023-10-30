<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-19
  Time: 00:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>page pret</title>
    <%@ include file="link_css.jsp"%>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container mb-lg-5 mt-lg-5">
    <h1 class="">LISTE PRETS EN RETARD</h1>
    <c:choose>
        <c:when test="${ empty prets}">
            <p class="alert alert-info">Aucun pret en retard</p>
        </c:when>
        <c:otherwise>
            <!-- Tableau qui liste les ouvrages -->
            <!-- Table with panel -->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view view-cascade gradient-card-header primary-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
                    <a href="" class="white-text mx-3">LISTE DES PRETS EN RETARD</a>
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
                                    <a>Numero Pret
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Nom Lecteur
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Designation Livre
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Date Prêt
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Date Retour
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href=""> AMANDE(en Ariary)
                                        <i class="fas fa-sort ml-1"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <!--Table head-->
                            <!--Table body-->
                            <tbody>
                            <c:forEach items="${prets}" var="mapPret">
                                <tr>
                                    <td><c:out value="${mapPret.idPret}"/></td>
                                    <td class="text-uppercase"><c:out value="${mapPret.lecteur.nomLecteur}"/></td>
                                    <td><c:out value="${mapPret.livre.designation}"/></td>
                                    <td><joda:format value="${mapPret.datePret}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                    <td><joda:format value="${mapPret.dateRetour}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                    <td>1 000</td>
                                </tr>
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
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp"%>
<c:import url="script.jsp"/>
</body>
</html>
