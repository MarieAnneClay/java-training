<%@ include file="staticPage/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${id}</div>
				<h1><spring:message code="label.edit" /></h1>

				<form:form modelAttribute="computerForm" id="addComputer" action="#" method="POST">
					<form:hidden path="id" value="${id}"/>
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message code="label.name" /></label> <form:input path="name"
								type="text" class="form-control" id="computerName"
								name="computerName" placeholder="Computer name"
								pattern="^[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$"
								value="${computer.getName()}" required="true"></form:input>
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="label.introduced" /></label> <form:input
							path="introduced"
								type="date"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								class="form-control" id="introduced" name="introduced"
								placeholder="Introduced date AAAA-MM-DD"
								value="${computer.getIntroduced()}"></form:input>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="label.discontinued" /></label> <form:input
							path="discontinued"
								type="date"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								class="form-control" id="discontinued" name="discontinued"
								placeholder="Discontinued date AAAA-MM-DD"
								value="${computer.getDiscontinued()}"></form:input>
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="label.company" /></label> <form:select
							path="company.id"
								class="input-lg form-control" id="companyId" name="companyId">
								<option></option>
								<c:forEach items="${companies}" var="company">
									<option value="${company.getId()}"
										<c:if test="${company.getId() == computer.getCompanyId()}"> selected </c:if> >
			                                                	${company.getName()}
			                                                	</option>
                                                </c:forEach>
									</form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
							<a class="btn btn-primary" id="addComputer" href="#"
								onclick="submitValidate();"><spring:message code="label.add" /></a> or <a
								href="
				   				<c:url value="/dashboard">
				   				</c:url>"
								class="btn btn-default"> <spring:message code="label.cancel" /> </a>
						</div>
                        <div id="error" style="color:red"></div>
                        <p style="${empty errors ? "color:green" : "color:red"}">
	                       	<c:forEach items="${errors }" var="e">
	                       		<c:out value="${e }"/><br/>
	                       	</c:forEach>
						</p>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>  
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addComputer.js"></script>

</body>
</html>
