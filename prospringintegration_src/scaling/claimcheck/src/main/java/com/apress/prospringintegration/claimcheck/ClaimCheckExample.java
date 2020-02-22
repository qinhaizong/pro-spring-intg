/*
 * Copyright 2002-2011 the original author or authors.
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

package com.apress.prospringintegration.claimcheck;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

public class ClaimCheckExample {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/claimcheck-context.xml");

        MessageChannel input = context.getBean("checkinChannel", MessageChannel.class);
        PollableChannel output = context.getBean("checkoutChannel", PollableChannel.class);

        Map<String, String> customerMap = new HashMap<String, String>();
        customerMap.put("firstName", "John");
        customerMap.put("lastName", "Smith");
        customerMap.put("address", "100 State Street");
        customerMap.put("city", "Los Angeles");
        customerMap.put("state", "CA");
        customerMap.put("zip", "90064");

        Message<Map<String, String>> message = MessageBuilder.withPayload(customerMap).build();
        input.send(message);

        Message<?> reply = output.receive();
        System.out.println("received: " + reply.getPayload());
    }
}
