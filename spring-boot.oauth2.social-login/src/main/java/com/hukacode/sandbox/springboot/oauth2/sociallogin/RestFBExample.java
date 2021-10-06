/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Examples of RestFB's Graph API functionality.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
@SuppressWarnings("deprecation")
public class RestFBExample {
  /** RestFB Graph API client. */
  private final FacebookClient facebookClient25;

  /**
   * Entry point. You must provide a single argument on the command line: a valid Graph API access
   * token.
   *
   * @param args Command-line arguments.
   * @throws IllegalArgumentException If no command-line arguments are provided.
   */
  public static void main(String[] args) {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    new RestFBExample().runEverything();
  }

  RestFBExample() {
    ScopeBuilder scopeBuilder = new ScopeBuilder();
    scopeBuilder.addPermission(FacebookPermissions.EMAIL);
    scopeBuilder.addPermission(FacebookPermissions.USER_ABOUT_ME);

    FacebookClient client = new DefaultFacebookClient(Version.LATEST);
    String loginDialogUrlString = client.getLoginDialogUrl("659572495004882", "localhost", scopeBuilder);
    out.println("loginDialogUrlString:" + loginDialogUrlString);

    AccessToken accessToken =
        new DefaultFacebookClient(Version.LATEST)
            .obtainAppAccessToken("659572495004882", "06843ccaf91c22f86cb1e865ed12df27");
    String token = accessToken.getAccessToken();
    facebookClient25 = new DefaultFacebookClient("EAAJX4LBEtNIBAO9vDtvlZAKEgtcZCfCvXHF2csh05kwdiZASbsA6jPxulC2ctpYPFobh7n4qGiOUOlb88ZCaURjI4ZAakBl9lqWzuCHmLYsbXUZBarPIi0ECiExvE1AUeqDHXbxOldzFBudwLyDP1ZBZBrKNuOgwHOJMVG7IqcR8rw8CFuN7jps9l26QlZCXwfImjFVb1t8ZACZCpVZBguoZBL9dkcI80gP8XoEs16OrKzjv7XS9WaYCZCz50ZA2a5vYzSS4AMZD", Version.LATEST);
  }

  void runEverything() {
    fetchObject();
//    fetchObjects();
    fetchObjectsAsJsonObject();
    fetchConnections();
//    fetchDifferentDataTypesAsJsonObject();
//    search();
    metadata();
    paging();
    selection();
    parameters();
    rawJsonResponse();
  }

  void fetchObject() {
    out.println("* Fetching single objects *");

    User user = facebookClient25.fetchObject("me", User.class);
//    Page page = facebookClient25.fetchObject("hukacode", Page.class);

    out.println("User name: " + user.getName());
//    out.println("Page likes: " + page.getLikes());
  }

  void fetchObjectsAsJsonObject() {
    out.println("* Fetching multiple objects at once as a JsonObject *");

    List<String> ids = new ArrayList<String>();
    ids.add("4");
    ids.add("http://www.imdb.com/title/tt0117500/");

    // Make the API call
    JsonObject results = facebookClient25.fetchObjects(ids, JsonObject.class);

    System.out.println(results.toString());

    // Pull out JSON data by key and map each type by hand.
    JsonMapper jsonMapper = new DefaultJsonMapper();
    User user = jsonMapper.toJavaObject(results.getString("4", "{}"), User.class);
    Url url =
        jsonMapper.toJavaObject(
            results.get("http://www.imdb.com/title/tt0117500/").toString(), Url.class);

    out.println("User is " + user);
    out.println("URL is " + url);
  }

  void fetchObjects() {
    out.println("* Fetching multiple objects at once *");

    FetchObjectsResults fetchObjectsResults =
        facebookClient25.fetchObjects(Arrays.asList("me", "cocacola"), FetchObjectsResults.class);

    out.println("User name: " + fetchObjectsResults.me.getName());
    out.println("Page likes: " + fetchObjectsResults.page.getLikes());
  }

  void fetchDifferentDataTypesAsJsonObject() {
    out.println("* Fetching different types of data as JsonObject *");

    JsonObject zuck = facebookClient25.fetchObject("4", JsonObject.class);
    out.println(zuck.get("name").toString());

    JsonObject photosConnection = facebookClient25.fetchObject("me/photos", JsonObject.class);
    JsonArray photosConnectionData = photosConnection.get("data").asArray();

    if (photosConnectionData.size() > 0) {
      String firstPhotoUrl = photosConnectionData.get(0).asObject().getString("source", null);
      out.println(firstPhotoUrl);
    }
  }

  /** Holds results from a "fetchObjects" call. */
  public static class FetchObjectsResults {
    @Facebook User me;

    @Facebook(value = "cocacola")
    Page page;
  }

  void fetchConnections() {
    out.println("* Fetching connections *");

    Connection<User> myFriends = facebookClient25.fetchConnection("me/friends", User.class);
    Connection<Post> myFeed = facebookClient25.fetchConnection("me/feed", Post.class);

    out.println("Count of my friends: " + myFriends.getData().size());

    if (!myFeed.getData().isEmpty())
      out.println("First item in my feed: " + myFeed.getData().get(0).getMessage());
  }

  void search() {
    out.println("* Searching connections *");

    Connection<User> targetedSearch =
        facebookClient25.fetchConnection(
            "search", User.class, Parameter.with("q", "Mark"), Parameter.with("type", "user"));

    out.println("Posts on my wall by friends named Mark: " + targetedSearch.getData().size());
  }

  void metadata() {
    out.println("* Metadata *");

    User userWithMetadata =
        facebookClient25.fetchObject("me", User.class, Parameter.with("metadata", 1));

    out.println(
        "User metadata: has albums? "
            + userWithMetadata.getMetadata().getConnections().hasAlbums());
  }

  void paging() {
    out.println("* Paging support *");

    Connection<User> myFriends = facebookClient25.fetchConnection("me/friends", User.class);
    Connection<Post> myFeed =
        facebookClient25.fetchConnection("me/feed", Post.class, Parameter.with("limit", 100));

    out.println("Count of my friends: " + myFriends.getData().size());

    if (!myFeed.getData().isEmpty())
      out.println("First item in my feed: " + myFeed.getData().get(0));

    for (List<Post> myFeedConnectionPage : myFeed)
      for (Post post : myFeedConnectionPage) out.println("Post from my feed: " + post);
  }

  void selection() {
    out.println("* Selecting specific fields *");

    User user = facebookClient25.fetchObject("me", User.class, Parameter.with("fields", "id,name"));

    out.println("User name: " + user.getName());
  }

  void parameters() {
    out.println("* Parameter support *");

    Date oneWeekAgo = new Date(currentTimeMillis() - 1000L * 60L * 60L * 24L * 7L);

    Connection<Post> filteredFeed =
        facebookClient25.fetchConnection(
            "me/feed",
            Post.class,
            Parameter.with("limit", 3),
            Parameter.with("until", "yesterday"),
            Parameter.with("since", oneWeekAgo));

    out.println("Filtered feed count: " + filteredFeed.getData().size());
  }

  void rawJsonResponse() {
    out.println("* Raw JSON *");
    out.println("User object JSON: " + facebookClient25.fetchObject("me", String.class));
  }
}
