<%--
  Created by IntelliJ IDEA.
  User: Patricia
  Date: 2019-09-18
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="link_css.jsp"/>
    <link rel="stylesheet" href="chart.css">
    <script src="chart.js"></script>
    <%@ include file="chart.js" %>
</head>
<body>
<c:import url="menu.jsp"/>

 <div class="container">
     <canvas id="myChart"></canvas>
 </div>
<c:import url="footer.jsp"/>
</body>
<script src="chart.js"></script>
<script type="text/javascript">
    var ctx = document.getElementById('myChart').getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'My First dataset',
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [0, 10, 5, 2, 20, 30, 45]
            }]
        },

        // Configuration options go here
        options: {}
    });
</script>
</html>
