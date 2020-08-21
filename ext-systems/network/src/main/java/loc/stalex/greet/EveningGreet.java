package loc.stalex.greet;

import loc.stalex.net.Greetabl;

public class EveningGreet extends Greetabl {

    @Override
    public String buildResponse(String userName) {
        return "Good evening, " + userName;
    }
}
