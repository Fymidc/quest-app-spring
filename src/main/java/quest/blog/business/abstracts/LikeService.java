package quest.blog.business.abstracts;

import java.util.List;
import java.util.Optional;

import quest.blog.entities.Like;
import quest.blog.entities.dtos.LikeCreateRequest;

public interface LikeService {

    List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like getOneLikeById(Long likeId);

    void deleteOneLikeById(Long likeId);

    Like createOneLike(LikeCreateRequest request);
    
}
