package io.pivotal.hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloSpringBootApplication.class)
public class TestSpringBootApplication {

    protected static final Integer ACCOUNT_1 = 1;
    protected static final String ACCOUNT_1_NAME = "Hello";

    @Autowired
    HelloSpringBootApplication application;

    @Test
    public void validGreetingId() {
        Logger.getGlobal().info("Start validGreetingId test");
        Greeting greeting = application.findById(1);

        Assert.assertNotNull(greeting);
        Assert.assertEquals(ACCOUNT_1,greeting.getId());
        Logger.getGlobal().info("End validGreetingId test");
    }

    @Test
    public void validGreetingText() {
        Logger.getGlobal().info("Start validGreetingText test");
        Greeting greeting = application.findByText("Hello");

        Assert.assertNotNull(greeting);
        Assert.assertEquals(ACCOUNT_1_NAME, greeting.getText());
        Logger.getGlobal().info("End validGreetingText test");
    }

    @Test
    public void validGreetingPartialText() {
        Logger.getGlobal().info("Start validGreetingPartialText test");
        List<Greeting> greetings = application.findByPartialText("He");

        Assert.assertNotNull(greetings);
        Assert.assertTrue(greetings.size()>0);
        Assert.assertEquals(ACCOUNT_1_NAME, greetings.iterator().next().getText());
        Logger.getGlobal().info("End validGreetingText test");
    }
}