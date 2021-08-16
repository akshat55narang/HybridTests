package rest.builders;

import pojo.User;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder withLogin(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder withId(String id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withNodeId(String nodeId) {
        user.setNodeId(nodeId);
        return this;
    }

    public UserBuilder withAvatarUrl(String avatarUrl) {
        user.setAvatarUrl(avatarUrl);
        return this;
    }

    public UserBuilder withGravatarId(String gravatarId) {
        user.setGravatarId(gravatarId);
        return this;
    }

    public UserBuilder withUrl(String url) {
        user.setUrl(url);
        return this;
    }

    public UserBuilder withHtmlUrl(String htmlUrl) {
        user.setHtmlUrl(htmlUrl);
        return this;
    }

    public UserBuilder withFollowersUrl(String followersUrl) {
        user.setFollowersUrl(followersUrl);
        return this;
    }

    public UserBuilder withFollowingUrl(String followingUrl) {
        user.setFollowingUrl(followingUrl);
        return this;
    }

    public UserBuilder withGistsUrl(String gistsUrl) {
        user.setGistsUrl(gistsUrl);
        return this;
    }

    public UserBuilder withType(String type) {
        user.setType(type);
        return this;
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withPrivateGists(long privateGists) {
        user.setPrivateGists(privateGists);
        return this;
    }

    public User build() {
        return user;
    }
}
