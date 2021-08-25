package quest.blog.entities.dtos;

import quest.blog.entities.Post;

public class PostResponse {
 //bu class ı postun user ıd ve username ile dönmesi için   
   private Long id;
   private Long userId;
   private String userName;
   private String title;
   private String text;

   public PostResponse(Post entity){
    this.id = entity.getId();
    this.userId = entity.getUser().getId();
    this.userName=entity.getUser().getUserName();
    this.title = entity.getTitle();
    this.text = entity.getText();
   }


public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public Long getUserId() {
    return userId;
}
public void setUserId(Long userId) {
    this.userId = userId;
}
public String getUserName() {
    return userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getText() {
    return text;
}
public void setText(String text) {
    this.text = text;
}

}
