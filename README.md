# SpringBootChatGPT
Calling ChatGPT from a Spring Boot app

## Configuration

Open the `application.properties` file and provide the following configuration settings:

1. OpenAI API key:
    ```shell
    openai.key={YOUR_API_KEY}
    ```

## Usage

Start the app from a terminal:
```shell
mvn spring-boot:run
```

Post to the generateEmail API

```
curl -X POST 'http://localhost:8080/generateEmail' \
--header 'Content-Type: application/json' \
--data-raw '{
    "prompt": "You have not paid the last 2 invoices"
}'

```