<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Informations concernant mes ordinateurs</title>

    <!-- Bootstrap CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/css/style.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="wrapper">
		<!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top container-fluid" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value="/home"/>"><i class="fa fa-arrow-left"> Retour au tableau de bord </i></a>
            </div>
            <!-- /.navbar-header -->

        </nav>
        <div id="page-wrapper" class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Modifier un ordinateur</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form action="" method="post" class="">
										<div class="form-group"> 
                                             <label for="name">Nom</label> 
                                            <input type="text" class="input-lg form-control" id="name" name="name" placeholder="Nom" value="${name}">
                                         </div> 
                                         <div class="form-group"> 
                                             <label for="name">Date d'introduction</label> 
                                            <input type="text" class="input-lg form-control" id="introduced" name="introduced" placeholder="Date d'introduction (dd/mm/yyyy)" value="${introduced}">
                                         </div> 
                                         <div class="form-group"> 
                                             <label for="name">Date de discontinuité</label> 
                                            <input type="text" class="input-lg form-control" id="discontinued" name="discontinued" placeholder="Date de discontinuité (dd/mm/yyyy)" value="${discontinued}">
                                         </div> 
                                         <div class="form-group">
                                            <label for="promotion">Entreprise</label>
                                            <select class="input-lg form-control" id="companyId" name="companyId">
                                            	<option value="0"></option>
                                                <c:forEach items="${controller.getCompanies()}" var="c">
                                                	<option value="${c.getId()}" <c:if test="${c.getId() == companyId}"> selected </c:if> >
			                                                	${c.getName()}
			                                                	</option>
                                                </c:forEach>
                                            </select>
                                        </div>                                         
                                         <div class="text-right"> 
                                             <button type="submit" class="btn btn-lg btn-primary">Mettre à jour</button> 
                                         </div> 
                                        <p style="${empty errors ? "color:green" : "color:red"}">
                                        	<c:out value="${result }"/><br/>
                                        	<c:forEach items="${errors }" var="e">
                                        		<c:out value="${e }"/><br/>
                                        	</c:forEach>
                                         </p>
                                    </form>
                                </div>
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="./resources/js/jquery-3.1.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>

</body>

</html>
