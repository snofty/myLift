package com.snofty.lift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snofty.model.People;
import com.snofty.util.TestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildingTest {
    Lift lift = new Lift();

    @BeforeClass
    private void setUp(){
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
    }

    @Test(dataProvider = "provideFloors")
    public void test(List<Floor> floors) {
        lift.setFloors(floors);
        lift.start(true);
    }

    @DataProvider
    private Object[][] provideFloors() {
        return new Object[][]{
                {TestUtils.getTestFixtureAsList("src/test/resources/data/up/floors_case1.json", Floor.class)},
                {TestUtils.getTestFixtureAsList("src/test/resources/data/up/floors_case2.json", Floor.class)}
        };
    }
}