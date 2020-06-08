<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<!-- 	Just some stuff you need -->
	<header>
		<div class="container">
	
			<c:choose>
			<c:when test="${not empty message }">
			  <p class="alert ${messageClass}">${message }</p>
			<%
			  session.setAttribute("message", null);
			  session.setAttribute("messageClass", null);
			%>
			</c:when>
			</c:choose>
			
			
			<c:set var="recurList" scope="application" value="${fn:split('Daily,Weekly,Bi-Weekly,Monthly', ',')}"/>
			<c:set var="update" scope="request" value="${param.update}"/>
			<c:choose>
				<c:when test="${update == 'true' }">
					<c:set var="id" scope="request" value="${param.id }"/>
					<c:set var="name" scope="request" value="${param.name }"/>
					<c:set var="feedingTime" scope="request" value="${param.feedingTime }"/>
					<c:set var="currentRecur" scope="request" value="${param.recurrence }"/>
					<c:set var="food" scope="request" value="${param.food }"/>
					<c:set var="notes" scope="request" value="${param.notes }"/>
					<c:set var="addOrUpdate" value="${'Update' }"/>
				</c:when>
				<c:otherwise>
					<c:set var="id" scope="request" value=""/>
					<c:set var="name" scope="request" value=""/>
					<c:set var="feedingTime" scope="request" value=""/>
					<c:set var="currentRecur" scope="request" value=""/>
					<c:set var="food" scope="request" value=""/>
					<c:set var="notes" scope="request" value=""/>
					<c:set var="addOrUpdate" value="${'Add' }"/>
				</c:otherwise>
			</c:choose>
			
			
			<!-- Page content -->
			<h1>eZoo <small>${addOrUpdate} Feeding Schedule</small></h1>
			<hr class="paw-primary">
			<c:out value="${currentRecur }"/>
			<form action="addFeedingSchedule" method="post" class="form-horizontal">
				<input type="hidden" name="update" value="${update}"/>
				<div class="form-group">
				    <label for="id" class="col-sm-4 control-label">ID</label>
				    <div class="col-sm-4">
				      	<input type="number" min="0" class="form-control" id="id" name="id" placeholder="ID" value="${id}" required="required"/>
				    </div>
				</div>
				<div class="form-group">
				    <label for="name" class="col-sm-4 control-label">Name</label>
				    <div class="col-sm-4">
				      	<input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${name}" required="required"/>
				    </div>
				</div>
				<div class="form-group">
				    <label for="feedingTime" class="col-sm-4 control-label">Feeding Time</label>
				    <div class="col-sm-4">
				      	<input type="text" class="form-control" id="feedingTime" name="feedingTime" placeholder="Feeding Time" value="${feedingTime }" required="required"/>
				    </div>
				</div>
				<div class="form-group">
				    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
				    <div class="col-sm-4">
				      	<select required="required" name="recurrence" class="form-control">
				      		<c:forEach var="recur" items="${recurList}">
				      			<option value="${recur}" ${recur == currentRecur? 'selected': ''}>${recur}</option>
				      		</c:forEach>
				      	</select>
				    </div>
				</div>
				<div class="form-group">
				    <label for="food" class="col-sm-4 control-label">Food</label>
				    <div class="col-sm-4">
				      	<input type="text" class="form-control" id="food" name="food" placeholder="Food" value="${food }" required="required"/>
				    </div>
				</div>
				<div class="form-group">
				    <label for="notes" class="col-sm-4 control-label">Notes</label>
				    <div class="col-sm-4">
				      	<input type="text" class="form-control" id="notes" name="notes" placeholder="Notes" value="${notes }"/>
				    </div>
				</div>
				<div class="form-group">
				    <div class="col-sm-offset-4 col-sm-1">
				      	<button type="submit" class="btn btn-primary"><c:out value="${addOrUpdate }"/></button>
				    </div>
				</div>
			</form>
			
		</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />