<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-17
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="link_css.jsp"%>
</head>
<body>
<%@include file="menu.jsp"%>

<div class="container pt-5">
    <p class="${empty form.erreurs ? 'alert alert-info' : 'alert alert-danger'}">${form.resultat}</p>
    <form action="ModificationPret" method="post" class="align-content-center">
        <section class="form-elegant taille align-content-center">
            <!--Form without header-->
            <div class="card">
                <div class="card-body mx-4">
                    <!--Header-->
                    <div class="text-center">
                        <h3 class="dark-grey-text mb-5"><strong>Modification du Pret</strong></h3>
                    </div>

                    <div class="md-form pb-3">
                        <input type="text"  readonly name="idPret" id="Form-pass4" class="form-control" value="<c:out value="${pret.idPret}"/>">
                        <label>Numero Pret</label>
                    </div>

                    <!--Body-->
                    <div class="md-form">
                        <input type="hidden" name="ancienIdLecteur" id="Form-email1" readonly class="form-control" value="<c:out value="${pret.lecteur.idLecteur}"/>">
                    </div>

                    <div class="md-form mb-5">
                        <select class="browser-default custom-select validate" name="idLecteur">
                            <c:choose>
                                <c:when test="${empty lecteurs}">
                                    <option value="" disabled selected>lecteur vide</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${pret.lecteur.idLecteur}" selected>${pret.lecteur.idLecteur} ${pret.lecteur.nomLecteur}</option>
                                    <c:forEach items="${lecteurs}" var="mapLecteur" varStatus="boucle">
                                        <option value="${mapLecteur.idLecteur}">${mapLecteur.idLecteur} ${mapLecteur.nomLecteur}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <span>${form.erreurs['idLecteur']}</span>
                    </div>
                    <!--Body-->

                    <div class="md-form">
                        <input type="hidden" name="ancienIdLivre"  readonly class="form-control" value="<c:out value="${pret.livre.idLivre}"/>">
                    </div>

                    <div class="md-form mb-5">
                        <select class="browser-default custom-select validate" name="idLivre">
                            <c:choose>
                                <c:when test="${empty livres}">
                                    <option value="" disabled selected>livre vide</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${pret.livre.idLivre}" selected>${pret.livre.idLivre}  ${pret.livre.designation}</option>
                                    <c:forEach items="${livres}" var="mapLivre" varStatus="boucle">
                                        <c:if test="${mapLivre.disponible == true}">
                                            <option value="${mapLivre.idLivre}">${mapLivre.idLivre}  ${mapLivre.designation}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <span>${form.erreurs['idLivre']}</span>

                        <!--Body-->
                        <div class="md-form">
                            <input type="text" name="datePret"  readonly class="form-control" value="<joda:format value="${pret.datePret}" pattern="dd/MM/yyyy HH:mm:ss"/>">
                            <label for="Form-email1">Date Pret </label>
                        </div>
                        <!--Body-->
                        <div class="md-form">
                            <input type="text" name="dateRetour"  readonly class="form-control" value="<joda:format value="${pret.dateRetour}" pattern="dd/MM/yyyy HH:mm:ss"/>">
                            <label for="Form-email1">Date Retour </label>
                        </div>
                    </div>

                    <div class="text-center mb-3">
                        <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Modifier</button>
                    </div>
                    <p class="font-small dark-grey-text text-right d-flex justify-content-center mb-3 pt-2"> retour :
                    </p>

                    <div class="row my-3 d-flex justify-content-center">
                        <!--Lecteur-->
                        <a type="button" class="btn btn-white btn-rounded mr-md-3 z-depth-1a" href="/gestionBibliotheque_war_exploded/lecteur">
                            <i class="fas fa-user"></i>
                        </a>
                        <!--Livre-->
                        <a type="button" class="btn btn-white btn-rounded mr-md-3 z-depth-1a" href="/gestionBibliotheque_war_exploded/livre">
                            <i class="fas fa-book"></i>
                        </a>
                        <!--Pret-->
                        <a type="button" class="btn btn-white btn-rounded z-depth-1a" href="/gestionBibliotheque_war_exploded/prets">
                            <i class="fas fa-book-reader"></i>
                        </a>
                    </div>

                </div>

                <!--Footer-->
                <div class="modal-footer mx-5 pt-3 mb-1">
                    <p class="font-small grey-text d-flex justify-content-end">
                        Ajouter une autre lecteur?
                        <a href="/gestionBibliotheque_war_exploded/lecteur"  class="blue-text ml-1"> Formulaire d'ajout</a>
                    </p>
                </div>

            </div>
            <!--/Form without header-->
        </section>
    </form>


</div>

<%@include file="script.jsp"%>
</body>
</html>
