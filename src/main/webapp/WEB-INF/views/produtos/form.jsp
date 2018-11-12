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
            <form:input path="titulo"/>
            <form:errors path="titulo"/>
        </div>
        <div>
            <label>Descrição</label>
            <form:textarea rows="10" cols="20" path="descricao"/>
            <form:errors path="descricao"/>
        </div>
        <div>
            <label>Páginas</label>
            <form:input path="paginas"/>
            <form:errors path="paginas"/>
        </div>
        <div>
            <label>Data de Lançamento</label>
            <form:input path="dataLancamento"/>
            <form:errors path="dataLancamento"/>
        </div>
        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label>
                <form:input path="precos[${status.index}].valor"/>
                <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
            </div>
        </c:forEach>
        <button type="submit">Cadastrar</button>
    </form:form>

</body>
</html>
