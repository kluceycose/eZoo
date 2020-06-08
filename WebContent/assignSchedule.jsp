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
			
			<!-- Page content -->
			<h1>eZoo <small>Assign Feeding Schedule</small></h1>
			<hr class="paw-primary">
			<form action="assignSchedule" method="post" class="form-horizontal">
				<div class="form-group">
				    <label for="animalId" class="col-sm-4 control-label">Choose Animal</label>
				    <div class="col-sm-4">
				      	<select required="required" name="animalId" class="form-control" onChange="setSchedule();">
				      		<c:forEach var="animal" items="${animals}">
				      			<option value="${animal.animalID}">${animal.name}</option>
				      		</c:forEach>
				      	</select>
				    </div>
				</div>
				<div class="form-group">
				    <label for="scheduleId" class="col-sm-4 control-label">Choose Schedule</label>
				    <div class="col-sm-4">
				      	<select required="required" name="scheduleId" class="form-control">
				      			<option value="-1"></option>
				      		<c:forEach var="schedule" items="${schedules}">
				      			<option value="${schedule.scheduleId}">${schedule.name}</option>
				      		</c:forEach>
				      	</select>
				    </div>
				</div>
				<div class="form-group">
				    <div class="col-sm-offset-4 col-sm-1">
				      	<button type="submit" class="btn btn-primary">Assign Schedule</button>
				    </div>
				</div>
			</form>
			
		</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	