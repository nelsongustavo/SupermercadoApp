<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cursos</title>
</head>
<body>
	<f:view>
		<h:outputText value="Sistema Escolar"
			style="font: 18px Arial; font-weight: bold;" />
		<p />
		<h:outputText value="Listagem de Cursos"
			style="font: 16px Arial; font-weight: bold; color: gray;" />
		<p />
		<h:form>
			<h:messages layout="table" errorStyle="color: red"
				infoStyle="color: green" warnStyle="color: orange"
				fatalStyle="color: gray" style="font: 14px Arial;"
				showSummary="false" showDetail="true" />
			<h:dataTable value="#{cursoManagedBean.cursos}" var="item" border="1"
				style="font: 14px Arial;">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Nome" />
					</f:facet>
					<h:outputText value="#{item.nome}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Semestres" />
					</f:facet>
					<h:outputText value="#{item.semestres}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Valor" />
					</f:facet>
					<h:outputText value="#{item.valor}">
						<f:convertNumber maxFractionDigits="2" groupingUsed="true"
							currencySymbol="R$" maxIntegerDigits="7" type="currency" />
					</h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="A&ccedil;&atilde;o" escape="false" />
					</f:facet>
					<h:commandLink actionListener="#{cursoManagedBean.excluir}">
						<f:attribute name="curso" value="#{item}" />
						<h:graphicImage value="/img/excluir.png" title="Excluir"
							style="border: none" />
					</h:commandLink>
				</h:column>
			</h:dataTable>
		</h:form>
		<p />
		<h:outputText value="Inclusão de Curso"
			style="font: 16px Arial; font-weight: bold; color: gray;" />
		<p />
		<h:form id="formulario" style="font: 14px Arial;">
			<h:panelGrid columns="2" style="font: 14px Arial;">
				<h:outputLabel value="Nome:" for="nome" />
				<h:panelGroup>
					<h:inputText id="nome" value="#{cursoManagedBean.curso.nome}"
						label="nome" size="50" maxlength="50" required="true" />
					<h:message for="nome" showSummary="true" showDetail="false" />
				</h:panelGroup>
				<h:outputLabel value="Semestres:" for="nome" />
				<h:panelGroup>
					<h:inputText id="semestres"
						value="#{cursoManagedBean.curso.semestres}" label="semestres"
						size="2" maxlength="2" required="true" />
					<h:message for="semestres" showSummary="true" showDetail="false" />
				</h:panelGroup>
				<h:outputLabel value="Valor:" for="nome" />
				<h:panelGroup>
					<h:inputText id="valor" value="#{cursoManagedBean.curso.valor}"
						label="valor" size="10" maxlength="10" required="true">
						<f:convertNumber maxFractionDigits="2" groupingUsed="true"
							currencySymbol="R$" maxIntegerDigits="7" type="currency" />
					</h:inputText>
					<h:message for="valor" showSummary="true" showDetail="false" />
				</h:panelGroup>
				<h:outputText />
				<h:panelGroup>
					<h:commandButton value="Salvar"
						actionListener="#{cursoManagedBean.salvar}" />
				</h:panelGroup>
			</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>