package com.example.demoJP.service;

import com.example.demoJP.model.posts.Post;
import com.example.demoJP.model.users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetPostsServiceTest {
    @Test
    void testGetPostsHasData(){
        GetPostsService getPostsService = new GetPostsService();
        Post[] posts = getPostsService.getPosts();
        Assert.assertEquals(true, posts.length>0 );
    }
}
