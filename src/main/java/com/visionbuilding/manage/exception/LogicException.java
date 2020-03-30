package com.visionbuilding.manage.exception;

/**
 * 
 * service层异常<br/>
 *
 * @author min.chen
 * @date: 2018年9月12日 下午11:56:22
 * @version 1.0
 * @since JDK 1.8
 */
public class LogicException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9170106699262120863L;

	private String code;

	public LogicException() {
	}

	public LogicException(String message) {
		super(message);
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(Throwable cause) {
		super(cause);
	}

	public LogicException(String code, String message) {
		super(message);
		this.code = code;
	}

	public LogicException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ExceptionBean getBean() {
		return super.getBean();
	}
}
