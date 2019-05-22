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
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * This class demonstrates how to send messages to brokers using provided {@link DefaultMQProducer}.
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("192.168.175.130:9876");
        producer.setVipChannelEnabled(true);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0); //失败重试时间
        for (int i = 0; i < 100; i++) {
            final int index = i;
            Message msgA = new Message("TopicTestA", "TagA", ("A Hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            Message msgB = new Message("TopicTestB", "TagB", ("B Hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            Message msgC = new Message("TopicTestC", "TagC", ("C Hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msgA, 1000 * 60 * 60);
            System.out.println("sync: " + sendResult.getMsgId());
//            producer.send(msgB, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println("async: " + sendResult.getMsgId());
//                }
//                @Override
//                public void onException(Throwable e) {
//                    System.out.println("no");
//                    e.printStackTrace();
//                }
//            });
//            producer.sendOneway(msgC);
        }
        producer.shutdown();
    }

}
