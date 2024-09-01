package com.chatop.api.exception;

/**
 * Dynamique ressource not found exception.
 */
public class ResourceNotFoundException extends RuntimeException implements ApiException {
  private final String errorMsg;
  private final String errorCode;

  /**
   * ressource not found exception.
   *
   * @param code error code
   */
  public ResourceNotFoundException(ErrorCode code) {
    super(code.getErrMsg());
    errorMsg = code.getErrMsg();
    errorCode = code.getErrCode();
  }

  /**
   * ressource not found exception.
   *
   * @param message error message
   */
  public ResourceNotFoundException(String message) {
    super(message);
    errorMsg = ErrorCode.RESOURCE_NOT_FOUND.getErrMsg();
    errorCode = ErrorCode.RESOURCE_NOT_FOUND.getErrCode();
  }

  @Override
  public String getErrorMsg() {
    return errorMsg;
  }

  @Override
  public String getErrorCode() {
    return errorCode;
  }
}
