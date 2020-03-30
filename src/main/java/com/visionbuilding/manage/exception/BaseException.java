package com.visionbuilding.manage.exception;
/**
 * 
 * 异常<br/>
 *
 * @author min.chen
 * @date: 2018年9月12日 下午11:56:11
 * @version 1.0
 * @since JDK 1.8
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4208397129338921027L;

	private ExceptionBean bean;

	public BaseException() {
		this.bean = new ExceptionBean();
	}

	public BaseException(String message) {
		super(message);
		this.bean = new ExceptionBean(message);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		this.bean = new ExceptionBean(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
		this.bean = new ExceptionBean(cause);
	}

	public ExceptionBean getBean() {
		return this.bean;
	}
}
