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

package com.apress.prospringintegration.channels.messagingtemplate;

import com.apress.prospringintegration.channels.core.Ticket;
import com.apress.prospringintegration.channels.core.TicketGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("messaging-template.xml");
        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketReceiver ticketReceiver = applicationContext.getBean(TicketReceiver.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        for (Ticket ticket : ticketGenerator.createTickets()) {
            problemReporter.openTicket(ticket);
        }
        new Thread(ticketReceiver).start();
    }
}
