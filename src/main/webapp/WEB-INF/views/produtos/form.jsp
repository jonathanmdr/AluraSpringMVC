<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <form action="/produtos" method="POST">
        <div>
            <label>Título</label>
            <input type="text" name="titulo">
        </div>
        <div>
            <label>Descrição</label>
            <textarea rows="10" cols="20" name="descricao"></textarea>
        </div>
        <div>
            <label>Páginas</label>
            <input type="text" name="paginas">
        </div>
        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label>
                <input type="text" name="precos[${status.index}].valor">
                <input type="hidden" name="precos[${status.index}].tipo">
            </div>
        </c:forEach>
        <button type="submit">Cadastrar</button>
    </form>

</body>
</html>
