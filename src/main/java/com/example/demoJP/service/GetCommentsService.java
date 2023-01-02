package com.example.demoJP.service;

import com.example.demoJP.model.comments.Comment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCommentsService {

    private RestTemplate restTemplate = new RestTemplate();
    public Comment[] getComments(){
        String url = "https://jsonplaceholder.typicode.com/users";
        return restTemplate.getForObject(url, Comment[].class);

    }
}
