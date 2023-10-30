<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-18
  Time: 11:46
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
    <h1 class="text-center">RESULTAT RECHERCHE</h1>
    <c:choose>
        <c:when test="${empty lecteurs}">
            <p class="alert alert-info">Aucun resultat trouvé</p>
        </c:when>
        <c:otherwise>
            <!-- Table with panel -->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view view-cascade gradient-card-header primary-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
                    <a href="" class="white-text mx-3">Liste lecteur</a>
                </div>
                <!--/Card image-->
                <div class="px-4">
                    <div class="table-wrapper">
                        <!--Table-->
                        <table class="table table-hover mb-0" id="datatable">
                            <!--Table head-->
                            <thead>
                            <tr>
                                <th class="th-lg">
                                    <a>Numero Lecteur
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Nom
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Prenom
                                    </a>
                                </th>
                                <th class="th-lg">
                                    <a href="">ACTION
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <!--Table head-->

                            <!--Table body-->
                            <tbody>
                            <c:forEach items="${lecteurs}" var="mapLecteurs" varStatus="boucle">
                                <tr>
                                    <td><c:out value="${mapLecteurs.idLecteur}"/></td>
                                    <td class="text-uppercase"><c:out value="${mapLecteurs.nomLecteur}"/></td>
                                    <td><c:out value="${mapLecteurs.prenomLecteur}"/></td>
                                    <td>
                                        <!-- bouton modifer -->
                                        <a  class="btn btn-outline-primary btn-rounded btn-sm px-2" href="<c:url value="/ModificationLecteur"><c:param name="idLecteur" value="${mapLecteurs.idLecteur}"/><c:param name="nomLecteur" value="${mapLecteurs.nomLecteur}"/> <c:param name="prenomLecteur" value="${mapLecteurs.prenomLecteur}"/> </c:url>">
                                            <i class="fas fa-pencil-alt mt-0"></i>
                                        </a>

                                        <!-- bouton et modal supprimer lecteur-->
                                        <!-- Button trigger modal-->
                                        <a onclick="return confirm('Etez-vous sûr de vouloir supprimer cet lecteur ?')" href="<c:url value="/suppressionLecteur"><c:param name="idLecteur" value="${mapLecteurs.idLecteur}"/></c:url>" class="btn btn-outline-danger btn-rounded btn-sm px-2" >
                                            <i class="far fa-trash-alt mt-0"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <!-- fin foreach-->
                            </tbody>
                            <!--Table body-->
                        </table>
                        <!--Table-->
                    </div>
                </div>
            </div>
            <!-- Table with panel -->
        </c:otherwise>
    </c:choose>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
