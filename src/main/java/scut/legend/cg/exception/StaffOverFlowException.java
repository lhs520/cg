package scut.legend.cg.exception;

/**
 * 每个月的入职员工数超过9999
 */
public class StaffOverFlowException extends RuntimeException{

	public StaffOverFlowException() {
		super();
	}

	public StaffOverFlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public StaffOverFlowException(String message) {
		super(message);
	}

	public StaffOverFlowException(Throwable cause) {
		super(cause);
	}

}
