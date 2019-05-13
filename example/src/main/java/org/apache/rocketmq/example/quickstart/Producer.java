/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.example.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * This class demonstrates how to send messages to brokers using provided {@link DefaultMQProducer}.
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        producerSync();

//        producerAsync();

//        producerOneway();
    }

    public static void producerSync() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("SYNC-NAME");

        producer.setNamesrvAddr("47.101.167.134:9876");
        producer.setVipChannelEnabled(false);

        producer.start();
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest", "TagA", ("SYNC Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }

    public static void producerAsync() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ASYNC-NAME");

        producer.setNamesrvAddr("47.101.167.134:9876");
        producer.setVipChannelEnabled(false);

        producer.start();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Message msg = new Message("TopicTest", "TagB", "OrderID188", ("ASYNC Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%s%n", e.getMessage());
                    }
                }, 3000);

            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }

    public static void producerOneway() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("ONE-WAY-NAME");

        producer.setNamesrvAddr("47.101.167.134:9876");
        producer.setVipChannelEnabled(false);

        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest", "TagC", ("ONE-WAY Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
        }

        producer.shutdown();
    }
}
