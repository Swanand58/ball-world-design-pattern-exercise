package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.strategy.StraightStrategy;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class BallWorldController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        post("/load", (request, response) -> {
            try {
                String strategy = request.queryParams("strategies");
                String type = request.queryParams("type");
                System.out.println(strategy);
                System.out.println(type);
                dis.loadBall(strategy, type);
                return gson.toJson(dis.balls);
            } catch (Exception e) {
                response.status(400);
                return gson.toJson("error");
            }

        });

        post("/switch", (request, response) -> {
            return gson.toJson("switch strategies");
        });

        get("/update", (request, response) -> {
            try {
                dis.updateBallWorld();
                return gson.toJson(dis.balls);
            } catch (Exception e) {
                response.status(400);
                return gson.toJson("error");
            }

        });

        post("/canvas/dims", (request, response) -> {
            try {
                Matcher m = Pattern.compile("\\d+").matcher(request.body());
                int[] res = new int[2];
                int k = 0;
                while (m.find()) {
                    res[k] = Integer.parseInt(m.group(0));
                    k++;
                }
                System.out.println(request.body());
                dis.setCanvasDims(new Point(res[0], res[1]));
                return gson.toJson("set canvas dimensions");
            } catch (Exception e) {
                response.status(400);
                return gson.toJson("error");
            }

        });

        get("/remove", (request, response) -> {
            try {
                dis.remove();
                return gson.toJson("removed some balls in ball world");
            } catch (Exception e) {
                response.status(400);
                return gson.toJson("error");
            }

        });
        
        redirect.get("ballworld", "/");

        get("/clear", (request, response) -> {
            try {
                dis.removeAll();
                return gson.toJson("removed all balls in ball world");
            } catch (Exception e) {
                response.status(400);
                return gson.toJson("error");
            }

        });
        exception(Exception.class, (e, request, response) -> {
            response.status(500);
            response.body(gson.toJson("Internal Server Error"));
        });
    }

    /**
     * Get the heroku assigned port number.
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
