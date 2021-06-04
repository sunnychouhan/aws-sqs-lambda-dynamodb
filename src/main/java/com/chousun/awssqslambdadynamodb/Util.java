package com.chousun.awssqslambdadynamodb;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;

import static java.util.Objects.nonNull;

public class Util {

    public static void logEnvironment(Object event, Context context, Gson gson) {
        LambdaLogger logger = nonNull(context) ? context.getLogger() : null;
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // log event details
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());
    }
}