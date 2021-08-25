package quest.blog.business.abstracts;

import java.util.List;
import java.util.Optional;

import quest.blog.entities.Comment;
import quest.blog.entities.dtos.CommentCreateRequest;
import quest.blog.entities.dtos.CommentResponse;
import quest.blog.entities.dtos.CommentUpdateRequest;

public interface CommentService {

    List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment getOneCommentById(Long commentId);

    Comment createOneComment(CommentCreateRequest request);

    Comment updateOneCommentById(Long commentId , CommentUpdateRequest request);

    void deleteOneCommentById(Long commentId);
    
}
