package com.example.lambda;

import com.example.lambda.Model.Request;
import com.example.lambda.Model.Response;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class LambdaExercise extends SpringBootRequestHandler<Request, Response> {
}
