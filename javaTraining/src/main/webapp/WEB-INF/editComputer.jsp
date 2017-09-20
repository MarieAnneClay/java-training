<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.getId()}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="editComputer" method="POST">
                        <input type="hidden" value="0" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" value="${computer.getName()}">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced"  name="introduced" placeholder="Introduced date" value="${computer.getIntroduced()}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${computer.getDiscontinued()}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="input-lg form-control" id="companyId" name="companyId">
                                            	<option value="0"></option>
                                                <c:forEach items="${companies}" var="company">
                                                	<option value="${company.getId()}" <c:if test="${company.getId() == computer.getCompanyId()}"> selected </c:if> >
			                                                	${company.getName()}
			                                                	</option>
                                                </c:forEach>
									</select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="
				   				<c:url value="/dashboard">
				   				</c:url>" class="btn btn-default">
				   				 Cancel
					   		</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>