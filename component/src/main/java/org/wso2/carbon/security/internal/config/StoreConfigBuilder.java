/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.security.internal.config;

import org.wso2.carbon.security.jaas.util.CarbonSecurityConstants;
import org.wso2.carbon.security.usercore.constant.ConnectorConstants;
import org.wso2.carbon.security.usercore.util.MySQLFamilySQLQueryFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration builder for stores.
 */
public class StoreConfigBuilder {

    /**
     * Build a IdentityStoreConfig from a file.
     * @param fileName Name of the configuration file.
     * @return Instance of IdentityStoreConfig.
     */
    public static IdentityStoreConfig buildIdentityStoreConfig(String fileName) throws FileNotFoundException {

        File file = Paths.get(CarbonSecurityConstants.getCarbonHomeDirectory().toString(), "conf", "security", 
                              fileName).toFile();

        Yaml yaml = new Yaml();
        Map<String, String> values = (Map<String, String>) yaml.load(new FileInputStream(file));

        Properties storeProperties = new Properties();
        values.forEach(storeProperties::put);

        return new IdentityStoreConfig(storeProperties);
    }

    /**
     * Build a CredentialStoreConfig from a file.
     * @param fileName Name of the configuration file.
     * @return Instance of CredentialStoreConfig.
     */
    public static CredentialStoreConfig buildCredentialStoreConfig(String fileName) throws FileNotFoundException {

        File file = Paths.get(CarbonSecurityConstants.getCarbonHomeDirectory().toString(), "conf", "security",
                fileName).toFile();

        Yaml yaml = new Yaml();
        Map<String, String> values = (Map<String, String>) yaml.load(new FileInputStream(file));

        Properties storeProperties = new Properties();
        values.forEach(storeProperties::put);

        String databaseType = values.get(ConnectorConstants.DATABASE_TYPE);

        if (databaseType == null || databaseType.equals("MySQL")) {
            Map<String, String> sqlQueries = new MySQLFamilySQLQueryFactory().getQueries();
            storeProperties.put(ConnectorConstants.SQL_QUERIES, sqlQueries);
        }

        return new CredentialStoreConfig(storeProperties);
    }
}
