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

package com.apress.prospringintegration.channels.prioritychannel;

import com.apress.prospringintegration.channels.core.Ticket;
import com.apress.prospringintegration.channels.core.TicketGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String contextName = "priority-channel.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();
        PriorityProblemReporter problemReporter = applicationContext.getBean(PriorityProblemReporter.class);
        PriorityTicketReceiver ticketReceiver = applicationContext.getBean(PriorityTicketReceiver.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }
        Thread consumerThread = new Thread(ticketReceiver);
        consumerThread.start();
    }
}
