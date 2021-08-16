package pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pojo.request.FileAttributes;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GistResponse {
    private String url;
    private String id;
    private String description;
    private boolean isPublic;
    private Map<String, FileAttributes> files;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("public")
    public boolean isPublic() {
        return isPublic;
    }

    @JsonProperty("public")
    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Map<String, FileAttributes> getFiles() {
        return files;
    }

    public void setFiles(Map<String, FileAttributes> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "GistResponse{" +
                "description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", files=" + files +
                '}';
    }
}
