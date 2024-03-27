package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void StartPageOpenedTest() throws InterruptedException {
        App.driver.get(App.BaseUrl);
        assertTrue(App.driver.getTitle().equalsIgnoreCase("Flask Auth Example"));
        Thread.sleep(3000);
        App.driver.close();
    }
}
