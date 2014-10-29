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
        
        post(new Route("/random"){
            @Override
            public Object handle(Request request, Response response){
                String joke = chuckjoke.getRandom();
                return joke;
            }
        });

        post(new Route("/id"){
            @Override
            public Object handle(Request request, Response response){
                Integer number = Integer.valueOf(request.queryParams("id"));
                String joke = chuckjoke.getSpecific(number);
                return joke;
            }
        });

        post(new Route("/setName"){
            @Override
            public Object handle(Request request, Response response){
                chuckjoke.alterName(request.queryParams("firstName"), request.queryParams("lastName"));
                response.status(200);
                return response;
            }
        });

        post(new Route("/clearName"){
            @Override
            public Object handle(Request request, Response response){
                chuckjoke.resetName();
                response.status(200);
                return response;
            }
        });
    }
}
