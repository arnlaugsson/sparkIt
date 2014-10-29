package is.ru.sparkit;

import net.joningi.icndb.ICNDBClient;
import net.joningi.icndb.Joke;

public class ChuckJoke {
    final ICNDBClient client = new ICNDBClient();

    public String getRandom(){
        return client.getRandom().getJoke();
    }

    public String getSpecific(int num){
        Joke joke = client.getById(num);
        return joke.getJoke();
    }

    public void alterName(String firstName, String lastName){
        client.setFirstName(firstName);
        client.setLastName(lastName);
    }

    public void resetName(){
        client.clearName();
    }
}
