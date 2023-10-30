<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-07-13
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<!--Navbar -->
<nav class="mb-1 navbar navbar-expand-lg navbar-dark primary-color lighten-1 text-center">
    <a class="navbar-brand" href="#">BOOKING</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-555"
            aria-controls="navbarSupportedContent-555" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent-555">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/livre"/>">Livre</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/lecteur"/>">Lecteur</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/prets" />">Prets</a>
            </li>
            <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/ListeRetard"/>">Liste pret en retard</a>
            </li>
        </ul>
    </div>
</nav>
<!--/.Navbar -->