/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.webdriver.testng.ch3.keyboard;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SliderNGTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        // FIXME: active wait for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    public void testSlider() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement slider = driver.findElement(By.name("my-range"));
        String initValue = slider.getAttribute("value");
        log.debug("The initial value of the slider is {}", initValue);

        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = slider.getAttribute("value");
        log.debug("The final value of the slider is {}", endValue);
        assertThat(initValue).isNotEqualTo(endValue);
    }

}
