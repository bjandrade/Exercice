<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
	<label class="">Língua oficial:</label>
	<span>${countryInfo.lang}</span><br/>

	<label>População:</label>
	<span>${countryInfo.population}</span><br/>

	<label>Moeda:</label>
	<span>${countryInfo.currency}</span><br/>

	<label>Capital:</label>
	<span>${countryInfo.capital}</span><br/>

	<table class="table table-striped">
		<tr>
			<th>stationName</th>
			<th>temperature</th>
			<th>dewPoint</th>
			<th>humidity</th>
			<th>clouds</th>
			<th>weatherCondition</th>
			<th>hectoPascAltimeter</th>
			<th>windDirection</th>
			<th>windSpeed</th>
			<th>lat</th>
			<th>lng</th>
		</tr>
		<c:forEach var="element" items="${countryInfo.weatherCityList}" varStatus="status">
			<tr>
				<td><c:out value="${element.stationName}"/></td>
				<td><c:out value="${element.temperature}"/></td>
				<td><c:out value="${element.dewPoint}"/></td>
				<td><c:out value="${element.humidity}"/></td>
				<td><c:out value="${element.clouds}"/></td>
				<td><c:out value="${element.weatherCondition}"/></td>
				<td><c:out value="${element.hectoPascAltimeter}"/></td>
				<td><c:out value="${element.windDirection}"/></td>
				<td><c:out value="${element.windSpeed}"/></td>
				<td><c:out value="${element.lat}"/></td>
				<td><c:out value="${element.lng}"/></td>
			</tr>
		</c:forEach>
	</table>

</div>