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

package com.apress.prospringintegration.social.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageHandler;

public class ImapMail {
    private static Logger LOG = LoggerFactory.getLogger(ImapMail.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/mail/imap-mail.xml");

        DirectChannel inputChannel = context.getBean("inputChannel", DirectChannel.class);

        inputChannel.subscribe(new MessageHandler() {
            public void handleMessage(Message<?> message) throws MessagingException {
                LOG.info("Message: " + message);
            }
        });
    }
}
