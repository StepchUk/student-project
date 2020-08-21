package stalex.greet;

import stalex.net.Greetabl;

public class MorningGreet extends Greetabl {

    @Override
    public String buildResponse(String userName) {
        return "Good morning, " + userName;
    }
}
