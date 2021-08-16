package pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileAttributes {
    private String fileName;
    private String type;
    private String language;
    private String rawUrl;
    private long size;
    private boolean truncated;
    private String content;

    @JsonProperty("filename")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("filename")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("raw_url")
    public String getRawUrl() {
        return rawUrl;
    }

    @JsonProperty("raw_url")
    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileAttributes{" +
                "fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", rawUrl='" + rawUrl + '\'' +
                ", size=" + size +
                ", truncated=" + truncated +
                ", content='" + content + '\'' +
                '}';
    }
}
