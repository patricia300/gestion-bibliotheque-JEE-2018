<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-19
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="link_css.jsp"/>
</head>
<body>
<div class="container pt-lg-5">
    <p class="alert alert-danger text-uppercase"> ${pret.lecteur.nomLecteur} doit payer une amande de 1 000Ariary</p>
    <a  href="<c:url value="/RendreEnRetard"><c:param name="idPret" value="${pret.idPret}"/></c:url>" class="btn btn-outline-success btn-rounded btn-sm px-2">
        <i class="fas fa-undo-alt"></i>
    </a>
</div>
</body>
<c:import url="script.jsp"/>
</html>
