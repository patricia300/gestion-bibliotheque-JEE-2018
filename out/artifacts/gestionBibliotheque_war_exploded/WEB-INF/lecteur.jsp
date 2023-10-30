<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-07-12
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>page livre</title>
    <%@ include file="link_css.jsp"%>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container mb-lg-5 mt-lg-5">
    <h1 class="">LECTEUR</h1>

    <div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Ajouter un lecteur</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="lecteur" method="post">
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-book-reader prefix grey-text"></i>
                            <input type="text" name="nomLecteur" required id="orangeForm-name" class="form-control validate" >
                            <label for="orangeForm-name">Nom</label>
                            <span>${form.erreurs['nomLecteur']}</span>
                        </div>
                        <div class="md-form mb-5">
                            <i class="fas fa-user prefix grey-text"></i>
                            <input type="text" id="orangeForm-name1" name="prenomLecteur" class="form-control validate">
                            <label data-error="wrong" data-success="right" for="orangeForm-name">prenom</label>
                            <span>${form.erreurs['prenomLecteur']}</span>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

     <!-- formulaire de recherche d'un lecteur -->
    <div class="modal fade" id="modalRegisterForm1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Recherche d'un lecteur</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="RechercheLecteur" method="post">
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-search prefix grey-text"></i>
                            <input type="text" name="lecteur" required id="orangeForm-name5" placeholder="rechercher" class="form-control validate" >
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-primary">Rechercher</button>
                    </div>
                </form>
            </div>
        </div>
    </div>




    <div class="btn-group text-right" role="group" aria-label="Basic example">
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm1"><i class="fas fa-search"></i>Recherche lecteur</a>
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm"><i class="fas fa-plus"></i>Ajouter un lecteur</a>
    </div>

    <c:choose>
        <c:when test="${empty lecteurs }">
            <p class="alert alert-info">Aucun lecteur enregistré</p>
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
                                    <td>LT0<c:out value="${mapLecteurs.idLecteur}"/></td>
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
                                        <a  class="btn btn-outline-primary btn-rounded btn-sm px-2" href="<c:url value="/ListePret"><c:param name="idLecteur" value="${mapLecteurs.idLecteur}"/></c:url>">
                                            <i class="fas fa-info mt-0"></i>
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

<%@ include file="footer.jsp"%>

<%@ include file="script.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datatable').DataTable();
    });
</script>
</body>
</html>
