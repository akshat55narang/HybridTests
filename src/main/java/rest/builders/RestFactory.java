package rest.builders;

public class RestFactory {
    public static GistBuilder getGistBuilder() {
        return new GistBuilder();
    }

    public static FileBuilder getFileBuilder() {
        return new FileBuilder();
    }

    public static UserBuilder getUserBuilder() {
        return new UserBuilder();
    }
}
