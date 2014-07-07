package behavioral.strategy.util;

import behavioral.strategy.util.*;

import java.io.*;

/**
 */

public class FileErrors implements Serializable {


    private Long errorId;

    private Long fileDetailId;

    private String errorType;

    private String description;

    private String errorCode;

    public FileErrors() {
    }

    public FileErrors(String errorCode, ErrorType errorType, String description) {
        this.errorCode = errorCode;
        this.errorType = errorType.getDescr();
        this.description = description;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    public Long getFileDetailId() {
        return fileDetailId;
    }

    public void setFileDetailId(Long fileDetailId) {
        this.fileDetailId = fileDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "FileErrors{" +
                "errorId=" + errorId +
                ", fileDetailId=" + fileDetailId +
                ", errorType=" + errorType +
                ", description='" + description + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
