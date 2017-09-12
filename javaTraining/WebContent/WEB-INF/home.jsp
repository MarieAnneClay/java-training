<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div id="page-wrapper" class="container-fluid">
        <br>
            <div class="row">
                <div class="col-lg-8 col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-laptop fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"> <c:out value="${controller.computers.size()}"/></div>
                                    <div class="huge-label">Ordinateurs</div>
                                </div>
                            </div>
                        </div>
                        <a href="<c:url value="/add_computer" />">
                            <div class="panel-footer">
                                <span class="pull-left">Ajouter un ordinateur</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-industry fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><c:out value="${controller.companies.size()}"/></div>
                                    <div class="huge-label">Entreprises</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-laptop fa-fw"></i> Gestion des ordinateurs
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-striped">
                                            <thead>
                                                <tr>                                                
                                                    <th><i class="fa fa-laptop fa-fw"></i> Nom</th>
                                                    <th><i class="fa fa-clock-o fa-fw"></i>Introduit</th>
                                                    <th><i class="fa fa-clock-o fa-fw"></i>Discontinué</th>
                                                    <th><i class="fa fa-industry fa-fw"></i> ID</th>
                                                    <th class="text-right">Action</th>                                                   
                                                </tr>
                                            </thead>
                                            <tbody>
												<c:forEach items="${currentComputers}" var="c">
													<tr>
													    <td>${c.name}</td>
													    <td>${c.introduced}</td>
													    <td>${c.discontinued}</td>
													    <td><c:out value="${c.companyId != 0 ? controller.getCompany(c.companyId).getName(): ''}"/></td>
													    <td  class="text-right">
													        <a href="<c:url value="/update_computer?id=${c.id}"/>" class="btn btn-sm btn-warning"><i class="fa fa-pencil"></i> Modifier</a>
                                                       		<a href="<c:url value="/home?id=${c.id}"/>" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> Supprimer</a>
													    </td>
													</tr>
												</c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="text-center">
                                            <ul class="pagination">
                                           		<c:forEach var="i" begin="1" end="${computerTotalPages}">
                                           			<li><a href="
                                           				<c:url value="/home">
                                           					<c:param name="computerPage" value="${i}"/>	
                                           				</c:url>">
                                           				${i}
                                           			</a></li>
                                           		</c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                            </div>
                            
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->

                   </div> 
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-4">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-industry fa-fw"></i> Gestion des entreprises
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-striped">
                                            <thead>
                                                <tr>                                                
                                                    <th>Nom</th>                                             
                                                </tr>
                                            </thead>
                                            <tbody>
												<c:forEach items="${currentCompanies}" var="c">
													<tr>
													    <td>${c.name}</td>
													</tr>
												</c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="text-center">
                                            <ul class="pagination">
                                           		<c:forEach var="i" begin="1" end="${companyTotalPages}">
                                           			<li><a href="
                                           				<c:url value="/home">
                                           					<c:param name="companyPage" value="${i}"/>	
                                           				</c:url>">
                                           				${i}
                                           			</a></li>
                                           		</c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                            </div>
                            
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                   </div> 
                </div>
                <!-- /.col-lg-6 -->
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