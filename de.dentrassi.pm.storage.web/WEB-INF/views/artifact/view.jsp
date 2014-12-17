<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h:main title="Artifact - ${fn:escapeXml(artifact.id) }">

<ul class="button-bar">
	<li><a class="pure-button" href="delete">Delete</a></li>
	<li><a class="pure-button"  href="<c:url value="/channel/${artifact.channel.id }/view"/>">Channel</a></li>
	<c:if test="${artifact.generator }">
	<li><a class="pure-button pure-button-primary" href="<c:url value="/artifact/${artifact.id }/generate"/>">Regenerate</a></li>
	   <c:if test="${not empty artifact.editTarget }">
	       <li><a class="pure-button pure-button-primary" href="<c:url value="${artifact.editTarget.render(pageContext) }"/>">Edit</a></li>
	   </c:if>
	</c:if>
	<li><a href="get">Download</a></li>
</ul>

<h2>Meta Data</h2>

<table>
<tr><th>Name:</th><td>${fn:escapeXml(artifact.name) }</td></tr>
</table>

<table>

<tr>
	<th>Namespace</th>
	<th>Key</th>
	<th>Value</th>
</tr>

<c:forEach items="${artifact.metaData }" var="entry">
	<tr>
		<td>${fn:escapeXml(entry.key.namespace) }</td>
		<td style="white-space: nowrap;">${fn:escapeXml(entry.key.key) }</td>
		<td style="white-space: pre;"><code>${fn:escapeXml(entry.value) }</code></td>
	</tr>
</c:forEach>

</table>

</h:main>