package quest.blog.entities.dtos;

import quest.blog.entities.Comment;

public class CommentResponse {
    private Long id;
    private String text;
    private Long userId;
    private String userName;
    private Long postId;


    
    public CommentResponse(Comment entity){
        this.id = entity.getId();
        this.text = entity.getText();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.postId = entity.getPost().getId();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


}
