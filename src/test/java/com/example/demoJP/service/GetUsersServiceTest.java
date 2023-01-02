package com.example.demoJP.service;

import com.example.demoJP.model.users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetUsersServiceTest {
    @Test
    void testGetUsersHasData(){
        GetUsersService getUsersService = new GetUsersService();
        User[] users = getUsersService.getUsers();
        Assert.assertEquals(true, users.length>0 );
    }
}
