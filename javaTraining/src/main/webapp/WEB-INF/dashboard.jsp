<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
        	<a href="
   				<c:url value="/dashboard">
   				</c:url>" class="navbar-brand">
   				 Application - Computer Database 
	   		</a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="dashboardTitle">
                <c:out value="${size}"/> Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET"  class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" value="${search}"/>
                    </form>
                </div>
                <div class="pull-right">
                	<a href="
		   				<c:url value="/addComputer">
		   				</c:url>" class="btn btn-success" id="addComputer">
		   				Add Computer
	   				</a>
	   				<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${currentComputers}" var="computer">
                    <tr>                    	
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
                        </td>                        
                        <td>
                        		   				<a href="
		   				<c:url value="/editComputer">
		   					<c:param name="computerId" value="${computer.id}"/>
		   				</c:url>" id="editComputer">
	                    <span aria-hidden="true">${computer.name}</span> </a>
                        </td>
                        <td>${computer.introduced}</td>
                        <td>${computer.discontinued}</td>
                        <td><c:out value="${computer.companyId != 0 ? serviceCompany.getCompany(computer.companyId).getName(): ''}"/></td>						
                    </tr> 
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        <ul class="pagination">
        <li>
                    <a href="
	   				<c:url value="/dashboard">
	   					<c:param name="computerPage" value="${computerPage == 1 ? computerPage : computerPage-1}"/>	
	   				</c:url>">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
	   		<c:forEach var="i" begin="${begin}" end="${end}">
	   			<li><a href="
	   				<c:url value="/dashboard">
	   					<c:param name="computerPage" value="${i}"/>	
	   				</c:url>">
	   				${i}
	   			</a></li>
	   			
	   		</c:forEach>
	   		<li>
                <a href="
	   				<c:url value="/dashboard">
	   					<c:param name="computerPage" value="${computerPage == computerTotalPages ? computerPage : computerPage+1}"/>	
	   				</c:url>">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
		</ul>
		

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="
	   				<c:url value="/dashboard">
	   					<c:param name="numberOfComputerByPage" value="10"/>	
	   				</c:url>" class="btn btn-default">10
                  </a>
            <a href="
	   				<c:url value="/dashboard">
	   					<c:param name="numberOfComputerByPage" value="50"/>	
	   				</c:url>" class="btn btn-default">50
                  </a>
            <a href="
	   				<c:url value="/dashboard">
	   					<c:param name="numberOfComputerByPage" value="100"/>	
	   				</c:url>" class="btn btn-default">100
                  </a>
        </div>
        </div>

    </footer>
<script src="./resources/js/jquery.min.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="./resources/js/dashboard.js"></script>

</body>
</html>