package com.example.demoJP.service;

import com.example.demoJP.model.comments.Comment;
import com.example.demoJP.model.users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetCommentsServiceTest {
    @Test
    void testGetCommentsHasData(){
        GetCommentsService getCommentsService = new GetCommentsService();
        Comment[] comments = getCommentsService.getComments();
        Assert.assertEquals(true, comments.length>0 );
    }
}
