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
package io.github.bonigarcia.webdriver.testng.ch5.network_connection;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkConnectionNGTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriver originalDriver = WebDriverManager.chromedriver().create();
        driver = new Augmenter().augment(originalDriver);
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test(enabled = false)
    public void testNetworkConnection() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");

        NetworkConnection networkConnection = (NetworkConnection) driver;
        networkConnection
                .setNetworkConnection(NetworkConnection.ConnectionType.ALL);
        NetworkConnection.ConnectionType connectionType = networkConnection
                .getNetworkConnection();
        log.debug("Network connection {}", connectionType);
    }

}
