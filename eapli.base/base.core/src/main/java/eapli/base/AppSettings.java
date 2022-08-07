/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String SECONDPROPERTIES_RESOURCE = "AGVServer.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String PRODUCT_BARCODE_FORMAT = "ProductBarcodeFormat";
    private static final String PHOTO_SELECTION = "PhotoSelection";

    private static final String KEYS_STORE_PASS_KEY = "keys_store_pass";

    private static final String TRUSTED_STORE_KEY = "trusted_store";
    private static final String SERVER_IP_KEY = "serverIP";
    private static final String SERVER_PORT_KEY = "serverPort";
    private static final String SECONDSERVER_PORT_KEY = "serverPort2";

    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    public AppSettings(int i){
        loadSecondProperties();
    }

    private void loadSecondProperties() {
        //System.out.println("Loading second properties");
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(SECONDPROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + SECONDPROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();
            System.out.println("Error loading properties");
            LOGGER.warn("Loading default properties", exio);
        }
    }
    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.base.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli"
                + ".base");
        this.applicationProperties.setProperty(PRODUCT_BARCODE_FORMAT, "UPC");
        this.applicationProperties.setProperty(PHOTO_SELECTION, "Window");
        this.applicationProperties.setProperty(SERVER_IP_KEY, "localhost");
        this.applicationProperties.setProperty(SERVER_PORT_KEY, "9999");
        this.applicationProperties.setProperty(SECONDSERVER_PORT_KEY, "9997");
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public String getProductBarcodeFormat() {
        return this.applicationProperties.getProperty(PRODUCT_BARCODE_FORMAT);
    }

    public String getPhotoSelection() {
        return this.applicationProperties.getProperty(PHOTO_SELECTION);
    }

    public String getServerIpKey() {
        return this.applicationProperties.getProperty(SERVER_IP_KEY);
    }

    public int getServerPortKey() {
        //System.out.println("Port: "+Integer.parseInt(this.applicationProperties.getProperty(SERVER_PORT_KEY)));
        return Integer.parseInt(this.applicationProperties.getProperty(SERVER_PORT_KEY));
    }

    public int getSecondServerPortKey() {
        return Integer.parseInt(this.applicationProperties.getProperty(SECONDSERVER_PORT_KEY));
    }

    public static String getTrustedStoreKey() {
        return TRUSTED_STORE_KEY;
    }

    public static String getKeyStorePass() {
        return KEYS_STORE_PASS_KEY;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(final String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
