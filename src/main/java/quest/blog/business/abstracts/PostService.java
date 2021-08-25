package quest.blog.business.abstracts;

import java.util.List;
import java.util.Optional;

import quest.blog.entities.Post;
import quest.blog.entities.dtos.PostCreateRequest;
import quest.blog.entities.dtos.PostResponse;
import quest.blog.entities.dtos.PostUpdateRequest;

public interface PostService {

    List<PostResponse> getAllPost(Optional<Long> userId);

    Post getOnePostById(Long postId);

    Post createOnePost(PostCreateRequest newPostRequest);

    Post updateOnePostById(Long postId, PostUpdateRequest updatePost);

    void deleteOnePostById(Long postId);
    
}
