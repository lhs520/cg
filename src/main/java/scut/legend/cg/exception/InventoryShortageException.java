package scut.legend.cg.exception;

/**
 * 产品库存不足
 * 库存有误
 * @author yaoyou
 *
 */
public class InventoryShortageException extends RuntimeException{

	public InventoryShortageException() {
		super();
	}

	public InventoryShortageException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryShortageException(String message) {
		super(message);
	}

	public InventoryShortageException(Throwable cause) {
		super(cause);
	}
	
}
