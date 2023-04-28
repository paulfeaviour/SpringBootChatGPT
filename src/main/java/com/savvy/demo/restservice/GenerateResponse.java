package com.savvy.demo.restservice;


public class GenerateResponse {
    private String email;

    GenerateResponse(String email) {  
      this.email = email;
    }

    public String getEmail() {
       return this.email;
    }    

}
