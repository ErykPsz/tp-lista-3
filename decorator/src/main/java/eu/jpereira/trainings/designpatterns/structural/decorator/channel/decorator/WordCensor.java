package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import java.util.List;
import java.util.regex.Pattern;

public class WordCensor extends SocialChannelDecorator {

    private final List<String> censoredWords;

    public WordCensor(List<String> censoredWords) {
        this.censoredWords = censoredWords;
    }

    @Override
    public void deliverMessage(String message) {
        String censored = censor(message);
        this.delegate.deliverMessage(censored);
    }

    private String censor(String msg) {
        String result = msg;
        for (String word : censoredWords) {
            String regex = "(?i)\\b" + Pattern.quote(word) + "\\b";
            result = result.replaceAll(regex, "###");
        }
        return result;
    }
}
