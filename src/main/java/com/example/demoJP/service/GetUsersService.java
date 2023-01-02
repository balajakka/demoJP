package com.example.demoJP.service;

import com.example.demoJP.model.users.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetUsersService {

    private RestTemplate restTemplate = new RestTemplate();
    public User[] getUsers(){
        String url = "https://jsonplaceholder.typicode.com/users";
        return restTemplate.getForObject(url, User[].class);

    }
}
