<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!-- 	stateless -->
<%@page session="false"%>

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
				<!-- 				<div id="langBox"><select id="lang" name="lang">  -->
				<%--  						<option value="en" selected><spring:message  --%>
				<%--  									code="label.en" /></option>  --%>
				<%--  						<option value="fr"><spring:message  --%>
				<%--  									code="label.fr" /></option>  --%>

				<!-- 					</select> -->
				<!-- 				</div> -->
				<spring:message code="label.language" />
				: <a href="?lang=en"><spring:message code="label.en" /></a> | <a
					href="?lang=fr"><spring:message code="label.fr" /></a>

				<security:authorize access="hasRole('ROLE_USER')">
					<!-- For login user -->
					<c:url value="/j_spring_securityurity_logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>

					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<h2>
							User : ${pageContext.request.userPrincipal.name} | <a
								href="javascript:formSubmit()"> Logout</a>
						</h2>
					</c:if>


				</security:authorize>
			</div>
		</div>

	</header>