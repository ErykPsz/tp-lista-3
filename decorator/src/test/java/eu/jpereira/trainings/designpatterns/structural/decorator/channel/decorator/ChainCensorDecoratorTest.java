package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {

    @Test
    public void testChain() {

        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        SocialChannelProperties props =
                new SocialChannelProperties().putProperty(
                        SocialChannelPropertyKey.NAME,
                        TestSpySocialChannel.NAME
                );

        SocialChannel channel = builder
                .with(new WordCensor(Arrays.asList("Microsoft")))
                .with(new URLAppender("http://x"))
                .with(new MessageTruncator(50))
                .getDecoratedChannel(props);

        channel.deliverMessage("Microsoft Windows");

        TestSpySocialChannel spy = (TestSpySocialChannel) builder.buildChannel(props);

        assertEquals("### Windows http://x", spy.lastMessagePublished());
    }
}
