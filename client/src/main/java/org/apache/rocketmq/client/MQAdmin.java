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
package org.apache.rocketmq.client;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Base interface for MQ management
 */
public interface MQAdmin {
    /**
     * 创建Topic (key、newTopic、queueNum) queueNum主题的队列号码
     */
    void createTopic(final String key, final String newTopic, final int queueNum)
        throws MQClientException;

    /**
     * 创建Topic
     *
     * @param key accesskey
     * @param newTopic topic name
     * @param queueNum 主题的队列号码
     * @param topicSysFlag 主题系统标志
     */
    void createTopic(String key, String newTopic, int queueNum, int topicSysFlag)
        throws MQClientException;

    /**
     * 获取消息队列偏移量，偏移量以毫秒为单位<br>。由于IO开销较大，请谨慎调用
     */
    long searchOffset(final MessageQueue mq, final long timestamp) throws MQClientException;

    /**
     * 获取最大偏移量
     */
    long maxOffset(final MessageQueue mq) throws MQClientException;

    /**
     * 获取最小偏移量
     */
    long minOffset(final MessageQueue mq) throws MQClientException;

    /**
     * 获取最早存储的消息时间
     */
    long earliestMsgStoreTime(final MessageQueue mq) throws MQClientException;

    /**
     * 根据消息id查询消息
     */
    MessageExt viewMessage(final String offsetMsgId) throws RemotingException, MQBrokerException,
        InterruptedException, MQClientException;

    /**
     * 查询消息
     */
    QueryResult queryMessage(final String topic, final String key, final int maxNum, final long begin, final long end)
            throws MQClientException, InterruptedException;

    /**
     * 根据消息id、主题 查询消息
     */
    MessageExt viewMessage(String topic, String msgId) throws RemotingException, MQBrokerException, InterruptedException, MQClientException;

}