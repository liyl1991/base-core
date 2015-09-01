package cn.liyl.core.exception;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * OSPA业务异常基类. 带有错误代码与错误信息. 用户在生成异常时既可以直接赋予错误代码与错误信息. 也可以只赋予错误代码与错误信息参数. 如ErrorCode=ORDER.LACK_INVENTORY
 * ,errorArg=without EJB 系统会从errors.properties中查出 ORDER.LACK_INVENTORY=Book <{0}> lack of inventory 最后返回错误信息为 Book
 * <without EJB> lack of inventory. 参考自springside项目。
 *
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -8138602623241348983L;

	/**
	 * 错误代码,默认为未知错误
	 */
	private String errorCode = "UNKNOW_ERROR";

	/**
	 * 错误信息中的参数
	 */
	protected String[] errorArgs = null;

	/**
	 * 兼容纯错误信息，不含error code,errorArgs的情况
	 */
	private String errorMessage = null;

	/**
	 * 错误信息的i18n ResourceBundle. 默认Properties文件名定义于{@link Constants#ERROR_BUNDLE_KEY}
	 */
	// static private ResourceBundle rb = ResourceBundle.getBundle(Constants.ERROR_BUNDLE_KEY, LocaleContextHolder
	// .getLocale());
	public BusinessException() {
		super();
	}

	public BusinessException(Throwable e) {
		super(e);
		this.errorMessage = ExceptionUtils.getStackTrace(e);
	}

	public BusinessException(String errorCode, String[] errorArgs) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public BusinessException(String errorCode, String errorMessage) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BusinessException(String errorCode, String errorMessage, String[] errorArgs) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorArgs = errorArgs;
	}

	public BusinessException(String errorCode, String[] errorArgs, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public BusinessException(String errorCode, String errorArg, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorArgs = new String[] { errorArg };
	}

	public BusinessException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	/**
	 * 获得出错信息. 读取i18N properties文件中Error Code对应的message,并组合参数获得i18n的出错信息.
	 */
	public String getMessage() {
		if (errorMessage != null) {
			return errorMessage;
		}
		if (super.getMessage() != null)
			return super.getMessage();
		// 如果errorMessage不为空,直接返回出错信息.
		// if (errorMessage != null) {
		return errorMessage;
		// }
		// 否则用errorCode查询Properties文件获得出错信息
		// String message;
		// try {
		// message = rb.getString(errorCode);
		// } catch (MissingResourceException mse) {
		// message = "ErrorCode is: " + errorCode + ", but can't get the message of the Error Code";
		// }

		// 将出错信息中的参数代入到出错信息中
		// if (errorArgs != null)
		// message = MessageFormat.format(message, (Object[]) errorArgs);
		//
		// return message + ", Error Code is: " + errorCode;

	}

	public String getMessageDetail() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}
}