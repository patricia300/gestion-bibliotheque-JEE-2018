<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-18
  Time: 21:59
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
<div class="container pt-lg-5">
    <h1 class="text-center pb-5">RESULTAT RECHERCHE</h1>
    <c:choose>
        <c:when test="${empty prets}">
            <p class="alert alert-info">Aucun resultat trouvé</p>
        </c:when>
        <c:otherwise>
            <!-- Tableau qui liste les ouvrages -->
            <!-- Table with panel -->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view view-cascade gradient-card-header primary-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
                    <a href="" class="white-text mx-3">LISTE DES PRETS</a>
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
                                    <a>Numero Prêt
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
                                    <a href="">Action
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
                                    <td>
                                        <div>
                                            <c:choose>
                                                <c:when test="${mapPret.rendu == false}">
                                                    <a href="<c:url value="/ModificationPret"><c:param name="idPret" value="${mapPret.idPret}"/></c:url>"  class="btn btn-outline-primary btn-rounded btn-sm px-2">
                                                        <i class="fas fa-pencil-alt mt-0"></i>
                                                    </a>
                                                    <button type="button" class="btn btn-outline-elegant btn-rounded btn-sm px-2">
                                                        <i class="far fa-trash-alt mt-0"></i>
                                                    </button>
                                                    <a  href="<c:url value="/RendrePret"><c:param name="idPret" value="${mapPret.idPret}"/></c:url>" class="btn btn-outline-success btn-rounded btn-sm px-2">
                                                        <i class="fas fa-undo-alt"></i>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="btn btn-outline-elegant btn-rounded btn-sm px-2">
                                                        <i class="fas fa-pencil-alt mt-0"></i>
                                                    </button>
                                                    <a onclick="return confirm('Etez-vous sûr de vouloir supprimer cet pret ?')" href="<c:url value="/SuppressionPret"><c:param name="idPret" value="${mapPret.idPret}"/></c:url>"  class="btn btn-outline-danger btn-rounded btn-sm px-2">
                                                        <i class="far fa-trash-alt mt-0"></i>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </td>
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
<c:import url="footer.jsp"/>
</body>
</html>