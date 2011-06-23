/**
 * 
 */
package com.sree.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author srinivasr
 *
 */
@SuppressWarnings("serial")
@Component(value = "userSignupBean")
@Scope(value = "request")
public class UserSignupBean extends BaseBean {

}
