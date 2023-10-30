<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-07-11
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/taglibs.jsp"%>
<html>
  <head>
    <title>Acceuil</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="mdb/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="mdb/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="mdb/css/style.css" rel="stylesheet">
  </head>
  <body style="background-color: #e1bee7 ">

  <!-- Start your project here-->

  <!--/.Navbar secondary color-->
  <nav class="navbar navbar-dark secondary-color mb-lg-5">
    <h5 class="text-light">BOOKING|</h5>
  </nav>
  <!--/.Navbar secondary color-->

  <div class="pt-lg-5 pr-5 mt-lg-5">
    <h5 class="text-center font-weight-bold mb-lg-5">GESTION BIBLIOTHECAIRE</h5>

    <div class="card-group">
      <!-- Card LIVRE-->
      <div class="card card-image ml-5 " style="background-image: url(https://mdbootstrap.com/img/Photos/Horizontal/Work/4-col/img%20%2814%29.jpg);">

        <!-- Content -->
        <div class="text-white text-center d-flex align-items-center rgba-black-strong py-5 px-4">
          <div>
            <h5 class="pink-text"><i class="fas fa-chart-pie"></i> LIVRE</h5>
            <h3 class="card-title pt-2"><strong>Lorem ipsum dolor sit amet</strong></h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat fugiat, laboriosam, voluptatem,
              optio vero odio nam sit officia accusamus minus error nisi architecto nulla ipsum dignissimos.
              Odit sed qui, dolorum!.</p>
            <a class="btn btn-pink" href="/gestionBibliotheque_war_exploded/livre"><i class="fas fa-clone left"></i>Visiter</a>
          </div>
        </div>

      </div>
      <!-- Card LIVRE-->


      <!-- Card LECTEUR-->
      <div class="card card-image ml-5" style="background-image: url(https://mdbootstrap.com/img/Photos/Horizontal/Work/4-col/img%20%2814%29.jpg);">

        <!-- Content -->
        <div class="text-white text-center d-flex align-items-center rgba-black-strong py-5 px-4">
          <div>
            <h5 class="pink-text"><i class="fas fa-chart-pie"></i>LECTEUR</h5>
            <h3 class="card-title pt-2"><strong>Lorem ipsum dolor sit amet</strong></h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat fugiat, laboriosam, voluptatem,
              optio vero odio nam sit officia accusamus minus error nisi architecto nulla ipsum dignissimos.
              Odit sed qui, dolorum!.</p>
            <a class="btn btn-pink" href="/gestionBibliotheque_war_exploded/lecteur"><i class="fas fa-clone left"></i>Visiter</a>
          </div>
        </div>

      </div>
      <!-- Card LECTEUR-->

      <!-- Card PRETS-->
      <div class="card card-image ml-5" style="background-image: url(https://mdbootstrap.com/img/Photos/Horizontal/Work/4-col/img%20%2814%29.jpg);">

        <!-- Content -->
        <div class="text-white text-center d-flex align-items-center rgba-black-strong py-5 px-4">
          <div>
            <h5 class="pink-text"><i class="fas fa-chart-pie"></i>PRETS</h5>
            <h3 class="card-title pt-2"><strong>Lorem ipsum dolor sit amet</strong></h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat fugiat, laboriosam, voluptatem,
              optio vero odio nam sit officia accusamus minus error nisi architecto nulla ipsum dignissimos.
              Odit sed qui, dolorum!.</p>
            <a class="btn btn-pink" href="/gestionBibliotheque_war_exploded/prets"><i class="fas fa-clone left"></i>Visiter</a>
          </div>
        </div>

      </div>
      <!-- Card PRETS-->
    </div>

  </div>
<%@ include file="WEB-INF/footer.jsp"%>

  <!-- Start your project here-->

  <!-- SCRIPTS -->
  <!-- JQuery -->
  <script type="text/javascript" src="mdb/js/jquery-3.4.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="mdb/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="mdb/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="mdb/js/mdb.min.js"></script>
  <script type="text/javascript">
  </script>
  </body>
</html>
