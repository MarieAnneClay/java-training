<%@ taglib tagdir="/WEB-INF/tags" prefix="page"%>
<%@ include file="staticPage/header.jsp"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!-- Bean -->
<jsp:useBean id="paramsFromRequest" scope="page"
	class="java.util.BeanURLParams" />
${paramsFromRequest.getParameterFromRequest(pageContext.request)}

<section id="main">
	<div class="container">
		<h1 id="dashboardTitle">
			<c:out value="${size}" />
			<spring:message code="label.size" />
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" value="${search}" />
					<input type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a href="
		   				<c:url value="/addcomputer">
		   				</c:url>"
					class="btn btn-success" id="addComputer"> <spring:message
						code="label.add" />
				</a>
				<%-- 				<c:if test="${pageContext.request.role == 'ADMIN'}"> --%>

				<!-- 					<a class="btn btn-default" id="editComputer" href="#" -->
				<!-- 						onclick="$.fn.toggleEditMode();">View</a> -->

				<%-- 				</c:if> --%>

				<security:authorize access="hasRole('ADMIN') and isAuthenticated()">
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">View</a>
				</security:authorize>


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

					<th class="editMode" style="width: 60px; height: 22px;"><security:authorize
							access="hasRole('ADMIN') and isAuthenticated()">
							<input type="checkbox" id="selectall" />
							<span style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
							</span>
						</security:authorize></th>
					<th><a ${paramsFromRequest.overrideParam("sort", "name")}
						${paramsFromRequest.overrideParam("order", orderName)}
						href="${pageContext.request.contextPath}/dashboard${linkGenerated}${paramsFromRequest.buildUrl()}">Computer
							name</a></th>
					<th><a
						${paramsFromRequest.overrideParam("sort", "introduced")}
						${paramsFromRequest.overrideParam("order", orderIntroduced)}
						href="${pageContext.request.contextPath}/dashboard${linkGenerated}${paramsFromRequest.buildUrl()}">Introduced
							date</a></th>
					<th><a
						${paramsFromRequest.overrideParam("sort", "discontinued")}
						${paramsFromRequest.overrideParam("order", orderDiscontinued)}
						href="${pageContext.request.contextPath}/dashboard${linkGenerated}${paramsFromRequest.buildUrl()}">Discontinued
							date</a></th>
					<th><a
						${paramsFromRequest.overrideParam("sort", "company.name")}
						${paramsFromRequest.overrideParam("order", orderCompany)}
						href="${pageContext.request.contextPath}/dashboard${linkGenerated}${paramsFromRequest.buildUrl()}">Company</a></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<tr>
						<td class="editMode"><security:authorize
								access="hasRole('ADMIN') and isAuthenticated()">
								<input type="checkbox" name="cb" class="cb"
									value="${computer.id}">
							</security:authorize></td>
						<td><a
							href="
		   				<c:url value="/editcomputer">
		   					<c:param name="computerId" value="${computer.id}"/>
		   				</c:url>"
							id="editComputer"> <span aria-hidden="true">${computer.name}</span>
						</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td><c:out value="${computer.getCompanyId() == 0 ? "" : serviceCompany(computer.getCompanyId()).getName()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<page:pagination currentPage="${currentPage}" size="${size}"
			numberOfComputerByPage="${numberOfComputerByPage}" />
	</div>

</footer>
<script>
	var i18n = {
		"view" : "<spring:message code="view" />",
		"edit" : "<spring:message code="edit" />",
		"confirm" : "<spring:message code="confirm" />"
	};
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>

</body>
</html>