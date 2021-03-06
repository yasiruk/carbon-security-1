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

package org.wso2.carbon.security.jaas.handler;

import org.wso2.carbon.security.jaas.HTTPCallbackHandlerFactory;
import org.wso2.carbon.security.jaas.util.CarbonSecurityConstants;

import javax.naming.Context;
import javax.naming.Name;
import java.util.Hashtable;

/**
 *
 */
public class SAMLCallbackHandlerFactory implements HTTPCallbackHandlerFactory {

    @Override
    public String getSupportedLoginModuleType() {
        return CarbonSecurityConstants.SAML_LOGIN_MODULE;
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
            throws Exception {
        return new SAMLCallbackHandler();
    }
}
