package com.dteel.manage.exception;

import java.io.Serializable;
/**
 * 
 * Description: TODO {一句话描述类是干什么的}<br/>
 *
 * @author min.chen
 * @date: 2018年9月12日 下午11:53:44
 * @version 1.0
 * @since JDK 1.8
 */
public class ExceptionBean implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = -1237910029592023116L;
	private int id;
	private String message;
	private Throwable throwable;

	public ExceptionBean() {
	}

	public ExceptionBean(String message) {
		this.message = message;
	}

	public ExceptionBean(String message, Throwable cause) {
		this.message = message;
		this.throwable = cause;
	}

	public ExceptionBean(Throwable cause) {
		this.throwable = cause;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getThrowable() {
		return this.throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
