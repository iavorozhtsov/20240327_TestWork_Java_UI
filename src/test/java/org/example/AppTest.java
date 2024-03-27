package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import  org.example.App;

import java.util.Locale;

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
    public void StartPageOpenedTest()
    {

        assertTrue(App.driver.getTitle().equalsIgnoreCase("Flask Auth Example"));
    }
}
