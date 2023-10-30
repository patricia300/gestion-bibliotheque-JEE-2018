<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-08-13
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%@include file="link_css.jsp"%>
    <style>
        .taille{
            width: 45%;
        }
    </style>
</head>

<body>
<%@include file="menu.jsp"%>

<div class="container pt-5">

    <p class="${empty form.erreurs ? 'alert alert-info' : 'alert alert-danger'}">${form.resultat}</p>

    <form action="ModificationLivre" method="post" class="align-content-center">
        <section class="form-elegant taille align-content-center">
            <!--Form without header-->
            <div class="card">
                <div class="card-body mx-4">
                    <!--Header-->
                    <div class="text-center">
                        <h3 class="dark-grey-text mb-5"><strong>Modification du Livre</strong></h3>
                    </div>

                    <!--Body-->
                    <div class="md-form">
                        <input type="text" name="idLivre" id="Form-email1" readonly class="form-control" value="<c:out value="${livre.idLivre}"/>">
                        <label for="Form-email1">Numero </label>
                        <span>${form.erreurs['idLivre']}</span>
                    </div>

                    <div class="md-form pb-3">
                        <input type="text" name="designation" id="Form-pass1" class="form-control" value="<c:out value="${livre.designation}"/>">
                        <label for="Form-pass1">designation</label>
                        <span>${form.erreurs['designation']}</span>
                    </div>


                    <div class="md-form pb-3">
                        <input type="text" name="auteur" id="Form-pass2" class="form-control" value="<c:out value="${livre.auteur}"/>">
                        <label for="Form-pass1">Auteur</label>
                        <span>${form.erreurs['auteur']}</span>
                    </div>

                    <div class="md-form pb-3">
                        <input type="text" name="disponible" readonly id="Form-pass3" class="form-control"
                               <c:if test="${livre.disponible == true}">value="oui"</c:if>
                               <c:if test="${livre.disponible == false}">value="non"</c:if>>
                        <label for="Form-pass1">Disponible</label>
                        <span>${form.erreurs['disponible']}</span>
                    </div>
                    <div class="md-form pb-3">
                        <input type="date" id="inputMDEx" name="dateEdition" value="${livre.dateEdition}">
                        <span>${form.erreurs['dateEdition']}</span>
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
                        Ajouter un autre livre?
                        <a href="/gestionBibliotheque_war_exploded/livre"  class="blue-text ml-1"> Formulaire d'ajout</a>
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
