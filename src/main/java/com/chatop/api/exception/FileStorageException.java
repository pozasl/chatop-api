package com.chatop.api.exception;

/**
 * File storage exception.
 */
public class FileStorageException extends RuntimeException {
  private final String errorMsg;
  private final String errorCode;

  /**
   * File storage exception.
   *
   * @param code error code
   */
  public FileStorageException(ErrorCode code) {
    super(code.getErrMsg());
    errorMsg = code.getErrMsg();
    errorCode = code.getErrCode();
  }

  /**
   * File storage exception.
   *
   * @param message error message
   */
  public FileStorageException(String message) {
    super(message);
    errorMsg = ErrorCode.FILE_STORAGE_FAILURE.getErrMsg();
    errorCode = ErrorCode.FILE_STORAGE_FAILURE.getErrCode();
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
