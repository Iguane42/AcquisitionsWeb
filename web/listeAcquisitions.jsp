<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-bordered table-striped">
    <thead>
        <tr>
            <td>Titre</td>
            <td>Date</td>
            <td>ID Auteur</td> 
            <td>Auteur</td>                    
        </tr>
    </thead>
    <tbody>            
        <c:forEach var="acquisitionE" items="${lAcquisitionR}">
            <tr>
            <td>${acquisitionE.titre}</td>
            <td>${acquisitionE.date}</td>
            <td>${acquisitionE.identiteAuteur}</td>
            <td>${acquisitionE.idAuteur}</td> 
            </tr>
        </c:forEach>
    </tbody>
</table>           

