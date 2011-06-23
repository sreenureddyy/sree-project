package com.sree.captcha;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

import org.apache.log4j.Logger;

import com.octo.captcha.service.image.ImageCaptchaService;

@SuppressWarnings("serial")
public class RegisterController implements Serializable{
    public static final String CAPTCHA_ID = "Sree";

    private static Logger logger = Logger.getLogger(RegisterController.class);
    
    private ImageCaptchaService m_imageCaptchaService;
    private String m_secureText;

    public void setImageCaptchaService(ImageCaptchaService i_imageCaptchaService) {
        m_imageCaptchaService = i_imageCaptchaService;
    }
   
    public String getSecureText() {
        return m_secureText;
    }
    public void setSecureText(String i_secureText) {
        m_secureText = i_secureText;
    }
   
    protected BufferedImage generateCaptcha() {
        try {
            return m_imageCaptchaService.getImageChallengeForID(CAPTCHA_ID);
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
   
    /** Paints Captcha Image
     *
     * @param g2d
     * @param obj
     */
    public void paintCaptcha(Graphics2D g2d, Object obj) {
        BufferedImage secureImage = generateCaptcha();
       
        try {
            g2d.setClip(0,0,secureImage.getWidth(), secureImage.getHeight());
            g2d.drawImage(secureImage, 0,0, null);
        } catch (Exception ex) {
            logger.error("Cannot generate captcha image", ex);
        }
    }

    /** Generates Random Text for displaying on the image */
    public String getRandomString() {
        String str=new  String("QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
         StringBuffer sb=new StringBuffer();
         Random r = new Random();
         int te=0;
         for(int i=1;i<=6;i++){
             te=r.nextInt(62);
             sb.append(str.charAt(te));
         }
        
         return sb.toString();
    }
	
	public void submit(){
		System.out.println("Captcha ----->>>>");
	}
}