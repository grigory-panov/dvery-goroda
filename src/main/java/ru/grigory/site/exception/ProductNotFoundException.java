package ru.grigory.site.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 28.09.14
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
