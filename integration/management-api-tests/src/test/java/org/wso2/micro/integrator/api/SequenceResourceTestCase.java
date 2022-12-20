/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.micro.integrator.api;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class SequenceResourceTestCase extends ManagementAPITest {

    private static String resourcePath = "sequences";

    @Test(groups = {"wso2.esb"}, description = "Test get Sequences resource")
    public void retrieveSequences() throws IOException {

        JSONObject jsonResponse = sendHttpRequestAndGetPayload(resourcePath, accessToken);
        verifyResourceCount(jsonResponse, 5);
        verifyResourceInfo(jsonResponse, new String[]{"AbcSequence", "fault", "main"});
    }

    @Test(groups = {"wso2.esb"}, description = "Test get Sequences resource for search key")
    public void retrieveSearchedSequences() throws IOException {
        JSONObject jsonResponse = sendHttpRequestAndGetPayload(resourcePath.concat("?searchKey=ABC"), accessToken);
        verifyResourceCount(jsonResponse, 1);
        verifyResourceInfo(jsonResponse, new String[]{"AbcSequence"});
    }

    @AfterClass(alwaysRun = true)
    public void cleanState() throws Exception {
        super.cleanup();
    }
}
