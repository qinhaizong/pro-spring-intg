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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

public class PriorityProblemReporter extends ProblemReporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityProblemReporter.class);

    @Override
    void openTicket(Ticket ticket) {
        Map<String, Object> messageHeader = new HashMap<String, Object>();
        messageHeader.put("priority", ticket.getPriority().ordinal());
        channel.send(new GenericMessage<Ticket>(ticket, messageHeader));
        LOGGER.info("Ticket Sent - " + ticket.toString());
    }
}
