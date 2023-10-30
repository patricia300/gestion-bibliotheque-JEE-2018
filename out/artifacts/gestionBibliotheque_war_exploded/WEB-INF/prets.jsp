<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-07-14
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>page pret</title>
    <%@ include file="link_css.jsp"%>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container mb-lg-5 mt-lg-5">
    <h1 class="">PRETS</h1>

    <div class="modal fade" id="modalRegisterForm1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Recherche entre deux dates</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="Recherche2date" method="post" >
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <input class="form-control mr-sm-2" type="text" placeholder="Nom" aria-label="Search" name="nom">
                        </div>
                        <div class="md-form mb-5">
                            <input class="form-control mr-sm-2" type="date" required placeholder="Date Debut" aria-label="Search" name="dateDebut">
                        </div>
                        <div class="md-form mb-5">
                            <input class="form-control mr-sm-2" type="date" required placeholder="Date Fin" aria-label="Search" name="dateFin">
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-outline-primary btn-rounded btn-sm my-0" type="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalRegisterForm2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Rechercher un pret</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="RecherchePret" method="post" >
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-envelope prefix grey-text"></i>
                            <input class="form-control mr-sm-2" type="text" id="orangeForm-name" aria-label="Search" name="pret">
                            <label data-error="wrong" data-success="right" for="orangeForm-name">Pret à chercher</label>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-outline-primary btn-rounded btn-sm my-0" type="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Ajouter un prêt</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="prets" method="post" >
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <select class="browser-default custom-select validate" name="idLecteur">
                                <c:choose>
                                    <c:when test="${empty lecteurs}">
                                        <option value="" disabled selected>lecteur vide</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="" disabled selected>Choisie un lecteur</option>
                                        <c:forEach items="${lecteurs}" var="mapLecteur" varStatus="boucle">
                                            <option value="${mapLecteur.idLecteur}"><c:out value="${mapLecteur.idLecteur}"/> ${mapLecteur.nomLecteur}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <span>${form.erreurs['idLecteur']}</span>
                        </div>
                        <div class="md-form mb-5">
                            <select class="browser-default custom-select validate" name="idLivre">
                                <c:choose>
                                    <c:when test="${empty livres}">
                                        <option value="" disabled selected>livre vide</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="" disabled selected>Choisie un livre</option>
                                        <c:forEach items="${livres}" var="mapLivre" varStatus="boucle">
                                            <c:if test="${mapLivre.disponible == true}">
                                                <option value="${mapLivre.idLivre}">${mapLivre.idLivre}  ${mapLivre.designation}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <span>${form.erreurs['idLivre']}</span>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-primary" type="submit">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="btn-group text-right" role="group" aria-label="Basic example">
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm2"><i class="fas fa-search"></i>Recherche pret</a>
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm"><i class="fas fa-plus"></i>Ajouter un pret</a>
        <a href="" class="btn btn-outline-primary btn-rounded waves-effect mb-4" data-toggle="modal" data-target="#modalRegisterForm1"><i class="fas fa-search"></i>Recherche entre deux dates</a>
    </div>

     <c:if test="${!empty form}">
         <p class=" alert alert-info">
                 ${form.resultat}
         </p>
     </c:if>

    <c:choose>
        <c:when test="${ empty prets}">
            <p class="alert alert-info">Aucun pret enregistré</p>
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
                                    <td>P0<c:out value="${mapPret.idPret}"/></td>
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
<%@ include file="footer.jsp"%>
<c:import url="script.jsp"/>
</body>
</html>

