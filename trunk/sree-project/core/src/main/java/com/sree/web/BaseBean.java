package com.sree.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

import com.sree.constants.CommonConstants;
import com.sree.domain.User;

@SuppressWarnings( { "serial","unused" })
@Component(value = "baseBean")
@Scope(value = "request")
public class BaseBean  implements Serializable {
	public static final String jstlBundleParam = "javax.servlet.jsp.jstl.fmt.localizationContext";
	protected final Log log = LogFactory.getLog(getClass());
	private String richFacesSkin = "DEFAULT";
	private int dataTableRows = 10;
	private int dataTablePages = 10;
	private String dataScrollerAlignment = "right";
	private String selectedSidebarPanelName = "Admin";
	
	private Logger logger = Logger.getLogger(BaseBean.class);

	private UserDetails user = new User();

	public UserDetails getUser() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}
	
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	public String getBundleName() {
		return getServletContext().getInitParameter(jstlBundleParam);
	}

	public ResourceBundle getBundle() {
		return ResourceBundle.getBundle(getBundleName(), getRequest().getLocale());
	}

	public String getRichFacesSkin() {
		return richFacesSkin;
	}

	public void setRichFacesSkin(String richFacesSkin) {
		this.richFacesSkin = richFacesSkin;
	}
	
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
	public String getText(String key) {
		String message;
		try {
			message = getBundle().getString(key);
		} catch (java.util.MissingResourceException mre) {
			log.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}
	
	public String getText(String key, Object arg) {
		if (arg == null) {
			return getBundle().getString(key);
		}
		MessageFormat form = new MessageFormat(getBundle().getString(key));
		if (arg instanceof String) {
			return form.format(new Object[] { arg });
		} else if (arg instanceof Object[]) {
			return form.format(arg);
		} else {
			log.error("arg '" + arg + "' not String or Object[]");
			return "";
		}
	}

	private void addFaceMessage(String key, Object arg, String clientId,
			FacesMessage.Severity s) {
		FacesMessage msg = new FacesMessage(getText(key, arg));
		msg.setSeverity(s);
		FacesContext.getCurrentInstance().addMessage(clientId, msg);
	}

	protected void addMessage(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_INFO);
	}
	protected void addWarnMessage(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_WARN);
	}
	
	protected void addWarnMessage(String key, Object arg) {
		addWarnMessage(key, arg, null);
	}
	
	protected void addWarnMessage(String key) {
		addWarnMessage(key, null, null);
	}
	
	
	protected void addMessage(String key, Object arg) {
		addMessage(key, arg, null);
	}

	protected void addMessage(String key) {
		addMessage(key, null);
	}

	protected void addError(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_ERROR);
	}

	protected void addError(String key, Object arg) {
		addError(key, arg, null);
	}

	protected void addError(String key) {
		addError(key, null);
	}

	public boolean hasErrors() {
		return (getSession().getAttribute("errors") != null);
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	public List<SelectItem> populate(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException("selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		selectItem.add(new SelectItem(CommonConstants.NOVALUE,"-- Select --"));
		populateData(selectItemList, selectItem);
		return selectItem;
	}

	private void populateData(List selectItemList, List<SelectItem> selectItem) {
		Object[] object = null;
		for (int i = 0; i < selectItemList.size(); i++) {
			object = (Object[]) selectItemList.get(i);
			selectItem.add(new SelectItem(object[0], (String) object[1]));
		}
	}

	public List<SelectItem> populateWithAll(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException("selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		selectItem.add(new SelectItem(CommonConstants.NOVALUE,"All"));
		populateData(selectItemList, selectItem);
		return selectItem;
	}
	public List<SelectItem> populateMultiCheckBox(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException("selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		populateData(selectItemList, selectItem);
		return selectItem;
	}

	public boolean getAreGlobalMessagesPresent() {
		Iterator<?> it = FacesContext.getCurrentInstance().getMessages(null);
		return it.hasNext();
	}

	public List<FacesMessage> getGlobalMessages() {
		List<FacesMessage> messages = new ArrayList<FacesMessage>();
		if (messages.isEmpty()) {
			Iterator<FacesMessage> it = FacesContext.getCurrentInstance()
					.getMessages(null);
			FacesMessage _msg = null;
			while (it.hasNext()) {
				_msg = it.next();
				messages.add(_msg);
			}
		}
		return messages;
	}

	public Object getSpringBean(String beanId) {
		Object appBean = null;
		Object webBean = null;
		Object facesBean = null;
		try {
			ApplicationContext appCtx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			appBean = appCtx.getBean(beanId);
			return appBean;
		} catch (Exception e) {

		}

		try {
			WebApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(getServletContext());
			webBean = ctx.getBean(beanId);
			return webBean;
		} catch (Exception e) {

		}
		try {
			facesBean = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().get(beanId);
			return facesBean;
		} catch (Exception e) {

		}

		return null;
	}
	
	public List<SelectItem> populateLabelsfromResourceBundle(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException("selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		selectItem.add(new SelectItem("", getText("dropdown.select")));
		Object[] object = null;
		for (int i = 0; i < selectItemList.size(); i++) {
			object = (Object[]) selectItemList.get(i);
			selectItem.add(new SelectItem(object[0],
					getText((String) object[1])));
		}
		return selectItem;
	}

	/*public static void uploadFile(UploadItem fileItem, String fileName)
			throws FileNotFoundException, IOException {
		InputStream fis = new FileInputStream(fileItem.getFile());
		FileOutputStream fos = new FileOutputStream(fileName);
		int i = 0;
		while ((i = fis.read()) != -1) {
			fos.write(i);
		}
		fos.close();
		fis.close();
	}*/

	public void downloadFile(String filePath, String fileName)
			throws IOException {
		int read = 0;
		byte[] bytes = new byte[1024];
		File file = new File(filePath + fileName);
		getResponse().setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
		FileInputStream fis = new FileInputStream(file);
		OutputStream os = getResponse().getOutputStream();
		while ((read = fis.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public String getLoginUserName(){
		return getUser().getUsername();
	}
	
	public String getLoginUserPassword(){
		return getUser().getPassword();
	}
	
	public Timestamp getCreatedDateTime(){
		return new Timestamp(new Date().getTime());
	}

	public int getDataTableRows() {
		return dataTableRows;
	}

	public void setDataTableRows(int dataTableRows) {
		this.dataTableRows = dataTableRows;
	}

	public int getDataTablePages() {
		return dataTablePages;
	}

	public void setDataTablePages(int dataTablePages) {
		this.dataTablePages = dataTablePages;
	}

	public String getDataScrollerAlignment() {
		return dataScrollerAlignment;
	}

	public void setDataScrollerAlignment(String dataScrollerAlignment) {
		this.dataScrollerAlignment = dataScrollerAlignment;
	}

	public String getSelectedSidebarPanelName() {
		return selectedSidebarPanelName;
	}

	public void setSelectedSidebarPanelName(String selectedSidebarPanelName) {
		this.selectedSidebarPanelName = selectedSidebarPanelName;
	}
	
}
