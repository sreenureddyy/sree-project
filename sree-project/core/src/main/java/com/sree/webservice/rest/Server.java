/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
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
package com.sree.webservice.rest;

import org.apache.cxf.binding.http.HttpBindingFactory;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.service.invoker.BeanInvoker;

public final class Server {
    private Server() { }

    public static void main(String[] args) throws Exception {
        OrderProcessImpl bs = new OrderProcessImpl();

        createRestService(bs);


        System.out.println("Started OrderProcess service!");

        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);

    }

    private static void createRestService(Object serviceObj) {
        // Build up the server factory bean
        JaxWsServerFactoryBean sf = new JaxWsServerFactoryBean();
        sf.setServiceClass(OrderProcess.class);
        // Use the HTTP Binding which understands the Java Rest Annotations
        sf.setBindingId(HttpBindingFactory.HTTP_BINDING_ID);
		System.out.println("DUDE " + HttpBindingFactory.HTTP_BINDING_ID);
        sf.setAddress("http://localhost:8080/xml/");
        sf.getServiceFactory().setInvoker(new BeanInvoker(serviceObj));

        // Turn the "wrapped" style off. This means that CXF won't generate
        // wrapper XML elements and we'll have prettier XML text. This
        // means that we need to stick to one request and one response
        // parameter though.
        sf.getServiceFactory().setWrapped(false);

        sf.create();
    }


}
