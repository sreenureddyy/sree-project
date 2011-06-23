<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://richfaces.org/rich" prefix="rich"%><%@taglib
	uri="http://richfaces.org/a4j" prefix="a4j"%><%@taglib
	uri="https://ajax4jsf.dev.java.net/ajax" prefix="ajax"%><%@taglib
	uri="http://myfaces.apache.org/tomahawk" prefix="t"%><f:view>
	<h:form>
		<t:saveState value="" id=""/>
		<form><input type="submit" style="float: right; vertical-align: top; text-align: right"></form>
		<a4j:commandLink target="" >
			<a4j:commandButton image="images/home.png" title="Home" action="home" />
		</a4j:commandLink>
		<h:commandLink>
			<a4j:actionparam></a4j:actionparam>
			<h:outputText value="CommandLink"></h:outputText>
		</h:commandLink>
			<h:outputText value="CommandLink"></h:outputText>
		</h:commandLink>
		<rich:dataTable value="#{home.userList}" binding="#{home.dataTable}" rows="10" var="row" >
		
		</rich:dataTable>
		<ajax:commandLink action="" value="" />
	</h:form>
</f:view>