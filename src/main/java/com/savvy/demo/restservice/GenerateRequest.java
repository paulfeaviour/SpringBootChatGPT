package com.savvy.demo.restservice;

//import com.fasterxml.jackson.annotation.JsonProperty;

class GenerateRequest {
    private String userPrompt;

    GenerateRequest() {}

    GenerateRequest(String prompt) {  
      this.userPrompt = prompt;
    }

    public String getUserPrompt() {
        return userPrompt;
    }
}

