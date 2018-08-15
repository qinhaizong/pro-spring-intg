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

package com.apress.prospringintegration.channels.queuechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;

public class TicketReceiver implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketReceiver.class);

    final static int RECEIVE_TIMEOUT = 1000;

    protected QueueChannel channel;

    public void setChannel(QueueChannel channel) {
        this.channel = channel;
    }

    public void handleTicketMessage() {
        Message<?> ticketMessage;
        while (true) {
            ticketMessage = channel.receive(RECEIVE_TIMEOUT);
            if (ticketMessage != null) {
                LOGGER.info("Received ticket - " + ticketMessage.getPayload().toString());
            } else {
                try {
                    /** Handle some other tasks **/
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        handleTicketMessage();
    }
}
