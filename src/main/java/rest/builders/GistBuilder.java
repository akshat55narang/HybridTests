package rest.builders;

import pojo.request.GistRequest;
import pojo.request.FileAttributes;

import java.util.Map;

public class GistBuilder {
    private GistRequest createGist;

    public GistBuilder() {
        createGist = new GistRequest();
    }

    public GistBuilder withDescription(String description) {
        createGist.setDescription(description);
        return this;
    }

    public GistBuilder isPublic(boolean isPublic) {
        createGist.setPublic(isPublic);
        return this;
    }

    public GistBuilder withFiles(Map<String, FileAttributes> files) {
        createGist.setFiles(files);
        return this;
    }

    public GistRequest build() {
        return createGist;
    }
}
