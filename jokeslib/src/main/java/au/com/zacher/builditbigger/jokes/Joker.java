package au.com.zacher.builditbigger.jokes;

import java.util.Random;

public class Joker {
    private static final String[] jokes = new String[] {
      "This is totally a funny joke. LOL",
      "What's brown and sticky? A stick.",
      "What's red and bad for your teeth? A brick.",
      "What's black white and read all over? A newspaper."
    };
    private static final Random rand = new Random();

    /**
     * Gets a random joke for your enjoyment
     * @return the joke string
     */
    public String getJoke() {
        return Joker.jokes[rand.nextInt(Joker.jokes.length)];
    }
}
