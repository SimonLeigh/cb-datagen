 /*
  * Copyright 2015 Couchbase, Inc.
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package com.couchbase.demo.iot.vehicles.cfg;

import java.io.IOException;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class SparkConfig extends BaseConfig {

    private static final String FILE_NAME = "spark.properties";
    
    private final String master;
    private final String bucket;
    private final String password;
    private final String cbCluster;
    private final String cbClusterSetting;
    
    
    public SparkConfig() throws IOException {
        super(FILE_NAME);
        
        this.master = this.props.getProperty("spark.master");
        this.bucket = props.getProperty("spark.cb.bucket.name");
        this.password = this.props.getProperty("spark.cb.bucket.pwd"); 
        this.cbCluster = this.props.getProperty("spark.cb.nodes");
        this.cbClusterSetting = this.props.getProperty("spark.cb.nodes.prop");
        
    
    }

    public String getMaster() {
        
        return this.master;
    }
    
    public String getBucket() {
          
        return this.bucket;
    }
    
    public String getPassword() {
        
        return this.password;
    }

    public String getCbCluster() {
        return cbCluster;
    }

    public String getCbClusterSetting() {
        return cbClusterSetting;
    }
    
    
}