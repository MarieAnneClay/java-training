<%@ taglib tagdir="/WEB-INF/tags" prefix="page"%>
<%@ include file="staticPage/header.jsp"%>

<!-- Bean -->
<jsp:useBean id="paramsFromRequest" scope="page"
	class="util.BeanURLParams" />
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
		   				<c:url value="/AddComputer">
		   				</c:url>"
					class="btn btn-success" id="addComputer"> <spring:message code="label.add" /> </a> <a
					class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();">View</a>
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

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><a ${paramsFromRequest.overrideParam("sort", "cr.name")}
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
						${paramsFromRequest.overrideParam("sort", "cy.name")}
						${paramsFromRequest.overrideParam("order", orderCompany)}
						href="${pageContext.request.contextPath}/dashboard${linkGenerated}${paramsFromRequest.buildUrl()}">Company</a></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
						<td><a
							href="
		   				<c:url value="/EditComputer">
		   					<c:param name="computerId" value="${computer.id}"/>
		   				</c:url>"
							id="editComputer"> <span aria-hidden="true">${computer.name}</span>
						</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td><c:out
								value="${computer.companyId != 0 ? serviceCompany.getCompany(computer.companyId).getName(): ''}" /></td>
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
        "view": "<spring:message code="view" />",
        "edit": "<spring:message code="edit" />",
        "confirm": "<spring:message code="confirm" />"
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