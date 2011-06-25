package com.sree.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.sree.constants.CommonConstants.ADDRESS_TYPE;
import com.sree.domain.Address;
import com.sree.domain.Authority;
import com.sree.domain.ContactDetails;
import com.sree.domain.User;
import com.sree.domain.UserAuthority;
import com.sree.service.IBaseService;
import com.sree.utils.CommonUtil;

@SuppressWarnings("serial")
@Component("userRegistration")
@Scope(value = "request")
public class UserRegistration extends BaseBean {
	public static final String CAPTCHA_ID = "Sree";

	private static Logger logger = Logger.getLogger(UserRegistration.class);

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Autowired
	private IBaseService baseService;

	private String m_secureText;
	private String reenterPassword;

	private User user = new User();
	private Address address = new Address();
	private ContactDetails contactDetails = new ContactDetails();

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}

	public String getSecureText() {
		return m_secureText;
	}

	public void setSecureText(String i_secureText) {
		m_secureText = i_secureText;
	}

	protected BufferedImage generateCaptcha() {
		try {
			return imageCaptchaService.getImageChallengeForID(CAPTCHA_ID);
		} catch (Exception ex) {
			logger.error("Cannot generate captcha image", ex);
			return null;
		}
	}

	public int getCaptchaWidth() {
		return 200;
	}

	public int getCaptchaHeight() {
		return 100;
	}

	/**
	 * Paints Captcha Image
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintCaptcha(Graphics2D g2d, Object obj) {
		BufferedImage secureImage = generateCaptcha();

		try {
			g2d.setClip(0, 0, secureImage.getWidth(), secureImage.getHeight());
			g2d.drawImage(secureImage, 0, 0, null);
		} catch (Exception ex) {
			logger.error("Cannot generate captcha image", ex);
		}
	}

	/** Generates Random Text for displaying on the image */
	public String getRandomString() {
		String str = new String(
				"QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= 6; i++) {
			te = r.nextInt(62);
			sb.append(str.charAt(te));
		}

		return sb.toString();
	}

	public String submit() {
		
		Address address = new Address();
		address.setCreatedBy("admin");
		address.setAddressType((long)ADDRESS_TYPE.user.ordinal());
		List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
		contactDetails.setCreatedBy("admin");
		contactDetailsList.add(contactDetails);
		address.setContactDetails(contactDetailsList);
		baseService.save(address);
		
		//user.setAddress(address);
		user.setPassword(CommonUtil.encript(user.getPassword()));
		user.setCreatedBy("admin");
		baseService.save(user);
		
		UserAuthority userAuthority = new UserAuthority();
		
		Authority authority = new Authority();
		authority.setId(1l);

		userAuthority.setAuthority(authority);
		userAuthority.setUser(user);
		userAuthority.setCreatedBy("admin");

		baseService.save(userAuthority);

		return "login";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getReenterPassword() {
		return reenterPassword;
	}

	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}

	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

}