package rest.builders;

import pojo.request.FileAttributes;

public class FileBuilder {
    private FileAttributes fileAttributes;

    public FileBuilder() {
        fileAttributes = new FileAttributes();
    }

    public FileBuilder withFileName(String fileName) {
        fileAttributes.setFileName(fileName);
        return this;
    }

    public FileBuilder withType(String type) {
        fileAttributes.setType(type);
        return this;
    }

    public FileBuilder withLanguage(String language) {
        fileAttributes.setLanguage(language);
        return this;
    }

    public FileBuilder withRawUrl(String rawUrl) {
        fileAttributes.setRawUrl(rawUrl);
        return this;
    }

    public FileBuilder withSize(long size) {
        fileAttributes.setSize(size);
        return this;
    }

    public FileBuilder isTruncated(boolean isTruncated) {
        fileAttributes.setTruncated(isTruncated);
        return this;
    }

    public FileBuilder withContent(String content) {
        fileAttributes.setContent(content);
        return this;
    }

    public FileAttributes build() {
        return fileAttributes;
    }

}
