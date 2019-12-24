package com.peishujuan.cms.util;
/**
 * 
 * @ClassName: CMSException 
 * @Description: 自定义异常
 * @author: a'su's
 * @date: 2019年12月18日 下午11:53:31
 */
public class CMSException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CMSException() {
		
	}
	public CMSException(String message) {
		super(message);
	}
}
