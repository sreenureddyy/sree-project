package com.sree.captcha;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.sree.web.UserRegistration;


/**
 * Validates inputed name for a project
 *
 * @author Sree
 */
public class CaptchaValidator implements Validator {

    public static final int MAX_NAME_LENGTH = 50;

    public static final String VALUEREQUIRED_ERROR_MESSAGE = "Value is required.";
    public static final String TEXTNOTMATCH_ERROR_MESSAGE = "Text not match text on image";
   
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        String val = StringUtils.strip((String)value);
        if (StringUtils.isEmpty(val)) {
            throw new ValidatorException(
                new FacesMessage(VALUEREQUIRED_ERROR_MESSAGE, VALUEREQUIRED_ERROR_MESSAGE)
            );
        }
       
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        WebApplicationContext appContext =
            WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        assert appContext != null;
       
        ImageCaptchaService imageCaptchaService = (ImageCaptchaService)appContext.getBean("imageCaptchaService");       

       if (!imageCaptchaService.validateResponseForID(UserRegistration.CAPTCHA_ID, val)) {
            throw new ValidatorException(
                new FacesMessage(TEXTNOTMATCH_ERROR_MESSAGE, TEXTNOTMATCH_ERROR_MESSAGE));
       }
    }
}
