<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-07-12
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>page livre</title>
<%@ include file="link_css.jsp"%>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container mb-lg-5 mt-lg-5">
    <h1 class="">LIVRE</h1>
    <!-- formulaire d'ajout d'un livre -->
    <div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form action="livre" method="post">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Ajouter un livre</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-book prefix grey-text"></i>
                            <input type="text"  name="designation" id="orangeForm-name" class="form-control validate">
                            <label data-error="wrong" data-success="right" for="orangeForm-name">Designation</label>
                            <span>${form.erreurs['designation']}</span>
                        </div>
                        <div class="md-form mb-5">
                            <i class="fas fa-pen-fancy prefix grey-text"></i>
                            <input type="text" id="orangeForm-name1" class="form-control validate" name="auteur">
                            <label data-error="wrong" data-success="right" for="orangeForm-name">Auteur</label>
                            <span>${form.erreurs['auteur']}</span>
                        </div>
                        <div class="md-form mb-5">
                            <i class="fas fa-calendar prefix grey-text"></i>
                            <input type="date" id="inputMDEx" name="dateEdition">
                        </div>

                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </div>
            </form>
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
                <form action="RechercheLivre" method="post">
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-search prefix grey-text"></i>
                            <input type="text" name="livre" required id="orangeForm-name5" placeholder="rechercher" class="form-control validate" >
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-primary">Rechercher</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="btn-group text-right right" role="group" aria-label="Basic example">
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm1"><i class="fas fa-search"></i>Recherche Livre</a>
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm"><i class="fas fa-plus"></i>Ajouter un livre</a>
    </div>
    <!-- Fin formulaire d'ajout d'un livre -->

    <c:if test="${!empty form}">
        <p class=" alert alert-danger">
                ${form.resultat}
        </p>
    </c:if>


    <!-- Liste de tous les livres existants -->
    <c:choose>
        <c:when test="${empty livres}">
            <p class="alert alert-info">Aucun livre enregistré</p>
        </c:when>
        <c:otherwise>

            <!-- Tableau qui liste les ouvrages -->
            <!-- Table with panel -->
            <div class="card card-cascade narrower">

                <!--Card image-->
                <div
                        class="view view-cascade gradient-card-header primary-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
                    <a href="" class="white-text mx-3">LISTE DES OUVRAGES</a>
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
                                    <a>Numero Livre</a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Designation</a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Auteur</a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Disponible</a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Date Edition</a>
                                </th>
                                <th class="th-lg">
                                    <a href="">Nombre x preté</a>
                                </th>
                                <th class="th-lg">
                                    <a href=""> ACTION</a>
                                </th>
                            </tr>
                            </thead>
                            <!--Table head-->
                            <!--Table body-->
                            <tbody>
                            <c:forEach items="${livres}" var="mapLivres" varStatus="boucle">
                                <tr>
                                    <td>LV0<c:out value="${mapLivres.idLivre}"/></td>
                                    <td><c:out value="${mapLivres.designation}"/></td>
                                    <td><c:out value="${mapLivres.auteur}"/></td>
                                    <td>
                                        <c:if test="${mapLivres.disponible == true}" >
                                            <c:out value="oui"/>
                                        </c:if>
                                        <c:if test="${mapLivres.disponible == false}">
                                            <c:out value="non"/>
                                        </c:if>
                                    </td>
                                    <td><c:out value="${mapLivres.dateEdition}"/></td>
                                    <td><c:out value="${mapLivres.nombrePret}"/></td>
                                    <td>
                                        <div>
                                            <a type="button" class="btn btn-outline-primary btn-rounded btn-sm px-2" href="<c:url value="/ModificationLivre"><c:param name="idLivre" value="${mapLivres.idLivre}"/></c:url>">
                                                <i class="fas fa-pencil-alt mt-0"></i>
                                            <a/>
                                            <!-- bouton suppression livre -->
                                                <c:choose>
                                                    <c:when test="${mapLivres.disponible == true}">
                                                        <a  onclick="return confirm('Etez-vous sûr de vouloir supprimer cet livre ?')" href="<c:url value="/suppressionLivre"><c:param name="idLivre" value="${mapLivres.idLivre}"/></c:url>" class="btn btn-outline-danger btn-rounded btn-sm px-2">
                                                        <i class="far fa-trash-alt mt-0"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                         <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-outline-elegant btn-rounded btn-sm px-2" data-toggle="modal" data-target="#basicExampleModal">
                                                    <i class="far fa-trash-alt mt-0"></i>
                                                </button>
                                                         <!-- Modal -->
                                                         <div class="modal fade" id="basicExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                                     aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Erreur de suppression</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Impossible de supprimer cet livre.Il est encore uliser dans un prêt.
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                     </c:otherwise>
                                                </c:choose>
                                            <!-- Fin bouton suppression livre -->
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
<%@ include file="footer.jsp"%>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="mdb/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="mdb/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="mdb/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="mdb/js/mdb.min.js"></script>
</body>
</html>
