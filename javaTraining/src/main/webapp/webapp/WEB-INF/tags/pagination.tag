<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Declaration of attribute -->
<!-- Page -->
<%@ attribute name="currentPage" required="false"
	type="java.lang.Integer" description="number of the current page show"%>
<%@ attribute name="size" required="true" type="java.lang.Integer"
	description="The number of total computer"%>
<%@ attribute name="numberOfComputerByPage" required="true"
	type="java.lang.Integer" description="Computers per page"%>

<!-- Initialisation of attribute -->
<!-- Page -->
<c:if test="${empty currentPage}">
	<c:set var="currentPage" value="0" />
</c:if>

<c:if test="${numberOfComputerByPage eq 0}">
	<c:set var="numberOfComputerByPage" value="10" />
</c:if>


<!-- Declaration of variable -->
<!-- Size -->
<c:set var="paramNameNumberOfComputerByPage"
	value="numberOfComputerByPage" />
<c:set var="tableOfSize" value="${[10,50,100]}" />
<!-- Page -->
<c:set var="url" value="dashboard" />
<c:set var="paramNameCurrentPage" value="currentPage" />
<c:set var="nbPageLeftRight" value="2" />
<c:set var="computerTotalPages"
	value="${Math.max(0.0, Math.ceil(size / numberOfComputerByPage) - 1)}" />
<c:set var="beginLoop" value="0" />
<c:set var="endLoop" value="${computerTotalPages}" />

<c:if test="${computerTotalPages > nbPageLeftRight*2+1}">
	<c:choose>
		<c:when test="${currentPage eq 0}">
			<c:set var="beginLoop" value="${0}" />
			<c:set var="endLoop" value="${nbPageLeftRight*2}" />
		</c:when>
		<c:when test="${currentPage eq 1}">
			<c:set var="beginLoop" value="${0}" />
			<c:set var="endLoop" value="${nbPageLeftRight*2}" />
		</c:when>
		<c:when test="${currentPage+1 eq computerTotalPages}">
		<c:set var="beginLoop" value="${computerTotalPages-nbPageLeftRight*2}" />
			<c:set var="endLoop" value="${computerTotalPages}" />
		</c:when>
		<c:when test="${currentPage eq computerTotalPages}">
		<c:set var="beginLoop" value="${computerTotalPages-nbPageLeftRight*2}" />
			<c:set var="endLoop" value="${computerTotalPages}" />
		</c:when>
		<c:otherwise>
			<c:set var="beginLoop" value="${currentPage-nbPageLeftRight}" />
			<c:set var="endLoop" value="${currentPage+nbPageLeftRight}" />
		</c:otherwise>
	</c:choose>
	
</c:if>

<!-- Bean -->
<jsp:useBean id="paramsFromRequest" scope="page"
	class="java.util.BeanURLParams" />
${paramsFromRequest.getParameterFromRequest(pageContext.request)}

<!-- HTML -->
<!-- page -->
<ul class="pagination">


	<!-- show First and Previous pagination -->
	<c:if test="${beginLoop > 0}">
	${paramsFromRequest.overrideParam(paramNameCurrentPage, 0)}
		<li><a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}"><span>&laquo;&laquo;</span></a></li>
			${paramsFromRequest.overrideParam(paramNameCurrentPage, currentPage-1)}
		
	
	
	<li><a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}"><span>&laquo;</span></a>
		</li>
	</c:if>

	<!-- show pagination numbers -->
	<c:forEach begin="${beginLoop}" end="${endLoop}" varStatus="loop">
        ${paramsFromRequest.overrideParam(paramNameCurrentPage, loop.index)}
        <li
			<c:if test="${currentPage eq loop.index}">class="active"</c:if>>
			<a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}">${loop.index+1}</a>
		</li>
	</c:forEach>

	<!-- show Last and Next pagination -->
	<c:if test="${endLoop < computerTotalPages}">
	${paramsFromRequest.overrideParam(paramNameCurrentPage, currentPage+1)}
	<li><a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}"><span>&raquo;</span></a>
		</li>
		
			${paramsFromRequest.overrideParam(paramNameCurrentPage, computerTotalPages)}
			<li><a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}"><span>&raquo;&raquo;</span></a>
		</li>

	</c:if>
</ul>

<!-- size -->
<div class="btn-group btn-group-sm pull-right" role="group">
	<c:forEach var="currentSize" items="${tableOfSize}">
        ${paramsFromRequest.overrideParam(paramNameNumberOfComputerByPage, currentSize)}
        ${paramsFromRequest.overrideParam(paramNameCurrentPage, 0)}
        <a
			href="${pageContext.request.contextPath}/${url}${linkGenerated}${paramsFromRequest.buildUrl()}"
			class="btn btn-default <c:if test="${numberOfComputerByPage == currentSize}">active</c:if>">${currentSize}</a>
	</c:forEach>
</div>