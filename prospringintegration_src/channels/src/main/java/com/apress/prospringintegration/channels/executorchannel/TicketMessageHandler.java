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

package com.apress.prospringintegration.channels.executorchannel;

import com.apress.prospringintegration.channels.core.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessageDeliveryException;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.MessageRejectedException;
import org.springframework.integration.core.MessageHandler;

public class TicketMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketMessageHandler.class);

    @Override
    public void handleMessage(Message<?> message) throws MessageHandlingException, MessageDeliveryException {
        Object payload = message.getPayload();
        if (payload instanceof Ticket) {
            LOGGER.info("Received ticket - " + payload.toString());
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received.");
        }
    }

}
