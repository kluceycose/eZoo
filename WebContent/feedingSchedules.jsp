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
			<h1>eZoo <small>Feeding Schedules</small></h1>
			<hr class="paw-primary">
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">Schedule ID</th>
						<th class="text-center">Schedule Name</th>
						<th class="text-center">Feeding Time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
						<th class="text-center">Assigned Animals</th>
						<th class="text-center">Options</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var = "schedule" items="${schedules}">
						<tr>
							<td><c:out value="${schedule.scheduleId}"/></td>
							<td><c:out value="${schedule.name}" /></td>
							<td><c:out value="${schedule.feedingTime}" /></td>
							<td><c:out value="${schedule.recurrence}" /></td>
							<td><c:out value="${schedule.food}" /></td>
							<td><c:out value="${schedule.notes}" /></td>
							<td><c:out value="${scheduledAnimals.get(schedule)}" /></td>
							<td>
								<!-- Update button -->
								<form method ="get" action="addFeedingSchedule" class="inline-form">
									<input type="hidden" name="update" value="true"/>
									<input type="hidden" name="id" value="${schedule.scheduleId}"/>
									<input type="hidden" name="name" value="${schedule.name}"/>
									<input type="hidden" name="feedingTime" value="${schedule.feedingTime}"/>
									<input type="hidden" name="recurrence" value="${schedule.recurrence}"/>
									<input type="hidden" name="food" value="${schedule.food}"/>
									<input type="hidden" name="notes" value="${schedule.notes}"/>
									<button type="submit">Update</button>
								</form>
								<!-- Delete button -->
								<form method="post" action="deleteFeedingSchedule" class="inline-form">
									<input type="hidden" name="scheduleId" value="${schedule.scheduleId}"/>
									<button type="submit" formaction="deleteFeedingSchedule">Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- Add a Schedule button -->
			<form method="get" action="addFeedingSchedule">
				<input type="hidden" name="update" value="false"/>
				<button type="submit" class="btn btn-primary">Add a Schedule</button>			
			</form>
		</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	
	