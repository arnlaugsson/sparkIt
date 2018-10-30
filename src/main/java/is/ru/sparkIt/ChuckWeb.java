package is.ru.sparkit;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

public class ChuckWeb implements SparkApplication {
    public static void main(String[] args){
        staticFileLocation("/public");
        SparkApplication chuckweb = new ChuckWeb();
        String port = System.getenv("PORT");
        if (port != null) {
            setPort(Integer.valueOf(port));
        }
        chuckweb.init(); 
    }

    public void init(){
        final ChuckJoke chuckjoke = new ChuckJoke();
        
        post("/random", (request, response) -> {
            String joke = chuckjoke.getRandom();
            return joke;
        });

        post("/id", (request, response) -> {
            Integer number = Integer.valueOf(request.queryParams("id"));
            String joke = chuckjoke.getSpecific(number);
            return joke;
        });

        post("/setName", (request, response) -> {
            String fn = request.queryParams("firstName");
            String ln = request.queryParams("lastName");
            chuckjoke.alterName(fn, ln);
            response.status(200);
            return response;
        });

        post("/clearName", (request, response) -> {
            chuckjoke.resetName();
            response.status(200);
            return response;
        });
    }
}
