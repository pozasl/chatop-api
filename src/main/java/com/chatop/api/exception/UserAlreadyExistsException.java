package com.chatop.api.exception;

/**
 * User already exist registration exception.
 */
public class UserAlreadyExistsException extends RuntimeException implements ApiException {
  private final String errorMsg;
  private final String errorCode;

  /**
   * User already exist registration exception.
   *
   * @param code error code
   */
  public UserAlreadyExistsException(ErrorCode code) {
    super(code.getErrMsg());
    errorMsg = code.getErrMsg();
    errorCode = code.getErrCode();
  }

  /**
   * User already exist registration exception.
   *
   * @param message error message
   */
  public UserAlreadyExistsException(String message) {
    super(message);
    errorMsg = ErrorCode.USER_ALREADY_EXISTS.getErrMsg();
    errorCode = ErrorCode.USER_ALREADY_EXISTS.getErrCode();
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
