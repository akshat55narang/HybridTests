package pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GistRequest {
    private String description;
    private boolean isPublic;
    private Map<String, FileAttributes> files;

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
        return "GistRequest{" +
                "description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", files=" + files +
                '}';
    }
}
