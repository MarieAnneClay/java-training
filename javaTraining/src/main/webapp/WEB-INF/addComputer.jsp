<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href="
   				<c:url value="/dashboard">
   				</c:url>"
				class="navbar-brand"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form name="addComputer" action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									pattern="^[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$"
									name="computerName" placeholder="Computer name" required>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date"
									pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
									class="form-control" id="introduced" name="introduced"
									placeholder="Introduced date AAAA-MM-DD">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date"
									pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
									class="form-control" id="discontinued" name="discontinued"
									placeholder="Discontinued date AAAA-MM-DD">
							</div>
							<div class="form-group">
								<label for="promotion">Company</label> <select
									class="input-lg form-control" id="companyId" name="companyId">
									<option value="0"></option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.getId()}">${company.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a
								href="
				   				<c:url value="/dashboard">
				   				</c:url>"
								class="btn btn-default"> Cancel </a>
						</div>
						<p style="${empty errors ? "color:green" : "color:red"}">
							<c:forEach items="${errors }" var="e">
								<c:out value="${e }" />
								<br />
							</c:forEach>
						</p>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="./resources/js/addComputer.js"></script>
</body>
</html>