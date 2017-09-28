<%@ include file="staticPage/header.jsp"%>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<form id="addComputer" action="#" method="POST">
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="computerName"
								pattern="^[0-9a-zA-Z������������ -]{1,60}$" name="computerName"
								placeholder="Computer name" required value="${name}">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								class="form-control" id="introduced" name="introduced"
								placeholder="Introduced date AAAA-MM-DD" value="${introduced}">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								class="form-control" id="discontinued" name="discontinued"
								placeholder="Discontinued date AAAA-MM-DD"
								value="${discontinued}">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="input-lg form-control" id="companyId" name="companyId">
								<option value="0"></option>
								<c:forEach items="${companies}" var="company">
									<option value="${company.getId()}"
										<c:if test="${company.getId() == companyId}"> selected </c:if> >
			                                                	${company.getName()}
			                                                	</option>
                                                </c:forEach>
									</select>
                            </div>
						</fieldset>
						<div class="actions pull-right">
							<a class="btn btn-primary" id="addComputer" href="#"
								onclick="submitValidate();">Add</a> or <a
								href="
				   				<c:url value="/dashboard">
				   				</c:url>"
								class="btn btn-default"> Cancel </a>
						</div>
						<div id="error" style="color:red"></div>
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
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addComputer.js"></script>

</body>
</html>
