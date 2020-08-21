package stalex.greet;

import stalex.net.Greetabl;

public class EveningGreet extends Greetabl {

    @Override
    public String buildResponse(String userName) {
        return "Good evening, " + userName;
    }
}
