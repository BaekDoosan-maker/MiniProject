package com.onetier.retro_together.scheduler;

import com.onetier.retro_together.domain.Post;
import com.onetier.retro_together.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final PostRepository postRepository;

    @Transactional
    @Scheduled(cron ="0 43 15 * * *")
    public void updatePostDelete() throws InterruptedException {
        System.out.println("게시글 업데이트 ");

        List<Post> postList = postRepository.findAll();

        boolean check = false;
        for (Post postOne : postList) {
            if(postOne.getComment_cnt()==0) {
                System.out.println("게시글" + postOne.getTitle() + "이 삭제되었습니다.");
                postRepository.delete(postOne);
                check = true;
            }
        }
        if(check == false) {
            System.out.println("삭제할 게시글이 없습니다.");
        }
    }
}

