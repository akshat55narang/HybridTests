package pojo.request;

import java.util.Map;

public class Files {
    private Map<String, FileAttributes> filesMap;

    public Map<String, FileAttributes> getFilesMap() {
        return filesMap;
    }

    public void setFilesMap(Map<String, FileAttributes> filesMap) {
        this.filesMap = filesMap;
    }
}
