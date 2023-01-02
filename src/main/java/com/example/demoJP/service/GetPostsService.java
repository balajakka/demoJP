package com.example.demoJP.service;

import com.example.demoJP.model.posts.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetPostsService {

    private RestTemplate restTemplate = new RestTemplate();
    public Post[] getPosts(){
        String url = "https://jsonplaceholder.typicode.com/posts";
        return restTemplate.getForObject(url, Post[].class);

    }
}
