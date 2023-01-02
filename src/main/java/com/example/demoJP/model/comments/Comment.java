package com.example.demoJP.model.comments;

import lombok.Data;

@Data
public class Comment {
    public float postId;
    public float id;
    public String name;
    public String email;
    public String body;
}
