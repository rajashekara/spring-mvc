<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<h4>${user.name}</h4>

<c:forEach items="${user.blogs}" var="blog">
<h4>${blog.name}</h4>
<p>${blog.url}</p>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
		<th>Title</th>
		<th>Link</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${blog.items}" var="item">
	<tr>
		<td>${item.title}</td>
		<td>${item.link}</td>
	</tr>
	</c:forEach>
	</tbody>
	
</table>
</c:forEach>