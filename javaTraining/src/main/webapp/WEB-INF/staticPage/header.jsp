<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="row">
				<a class="navbar-brand" href="<c:url value="/dashboard"/>">
					Application - <spring:message code="label.title" />
				</a>
				<div id="langBox">
					<spring:message
 							code="label.language" />: <select id="lang"> 
 						<option value="en" selected><spring:message 
 									code="label.en" /></option> 
 						<option value="fr"><spring:message 
 									code="label.fr" /></option> 

					</select>
				</div>
			</div>
		</div>

	</header>