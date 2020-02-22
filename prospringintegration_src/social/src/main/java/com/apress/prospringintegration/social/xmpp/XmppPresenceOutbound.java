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

package com.apress.prospringintegration.social.xmpp;

import org.jivesoftware.smack.packet.Presence;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class XmppPresenceOutbound {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/spring/xmpp/xmpp-presence-outbound.xml", XmppOutbound.class);

        MessageChannel input = context.getBean("xmppOutbound", MessageChannel.class);

        Presence presence =
                new Presence(Presence.Type.available, "Out to lunch", 0, Presence.Mode.away);
        Message<Presence> message = MessageBuilder.withPayload(presence).build();

        input.send(message);

        Thread.sleep(10 * 60 * 1000);
    }
}
