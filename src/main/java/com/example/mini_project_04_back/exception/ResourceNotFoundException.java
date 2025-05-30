package com.example.mini_project_04_back.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String title) {
        super(title + "이 존재하지 않습니다");
    }
}
