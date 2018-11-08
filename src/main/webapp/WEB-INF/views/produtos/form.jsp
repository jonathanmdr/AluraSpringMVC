<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jonat_000
  Date: 04/11/2018
  Time: 02:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Casa do Código</title>
</head>
<body>

    <form:form action="${s:mvcUrl('PC#gravar').build()}" method="POST" commandName="produto">
        <div>
            <label>Título</label>
            <input type="text" name="titulo">
            <form:errors path="titulo"/>
        </div>
        <div>
            <label>Descrição</label>
            <textarea rows="10" cols="20" name="descricao"></textarea>
            <form:errors path="descricao"/>
        </div>
        <div>
            <label>Páginas</label>
            <input type="text" name="paginas">
            <form:errors path="paginas"/>
        </div>
        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label>
                <input type="text" name="precos[${status.index}].valor">
                <input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}">
            </div>
        </c:forEach>
        <button type="submit">Cadastrar</button>
    </form:form>

</body>
</html>
