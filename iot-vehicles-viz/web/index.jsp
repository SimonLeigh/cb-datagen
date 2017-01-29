<%@page import="com.couchbase.demo.iot.vehicles.viz.TypeChart"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Vehicles Viz</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="js/bootstrap.min.js"></script>



        <script>

            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages':['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);
            
            //Mixed-in Java code
            <%
                TypeChart chart = new TypeChart();
                chart.drawChart();
            %>

        </script>


        <div class="container">

            <div style="margin: 30px" id="header">
                <a href = "index.jsp"><img src="images/cb.png" height="30px" alt="CB Graph Viz"/></a>
                <font size = "4" style="margin: 10px">Vehicles Viz</font>
                <div style="margin: 30px">
                    <a style="margin-left: 20px" href="index.jsp">Home</a>
                </div>
            </div>
            
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="type_chart"></div>
                    </div>
                </div>
            </div>     
        </div>

    </body>
</html>