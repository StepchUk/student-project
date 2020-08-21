package loc.stalex.greet;

import loc.stalex.net.Greetabl;

public class DayGreet extends Greetabl {

    @Override
    public String buildResponse(String userName) {
        return "Good day, " + userName;
    }
}
