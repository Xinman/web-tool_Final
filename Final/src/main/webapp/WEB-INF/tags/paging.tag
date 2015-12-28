<%@ tag language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true"
    type="org.springframework.beans.support.PagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<c:if test="${pagedListHolder.pageCount >1 }">
    <ul class="pagination">
     <c:if test="${!pagedListHolder.firstPage }"></c:if>
    </ul>
</c:if>


