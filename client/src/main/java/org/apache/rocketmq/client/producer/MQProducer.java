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
package org.apache.rocketmq.client.producer;

import java.util.Collection;
import java.util.List;

import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

public interface MQProducer extends MQAdmin {
    /**
     * 启动
     */
    void start() throws MQClientException;

    /**
     * 关闭
     */
    void shutdown();

    /**
     * 根据Topic 获取发布消息队列
     */
    List<MessageQueue> fetchPublishMessageQueues(final String topic) throws MQClientException;

    /**
     * 同步发送消息
     */
    SendResult send(final Message msg) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 同步发送消息、超时时间
     */
    SendResult send(final Message msg, final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 异步发送消息、回调函数
     */
    void send(final Message msg, final SendCallback sendCallback) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 异步发送消息、回调函数、超时时间
     */
    void send(final Message msg, final SendCallback sendCallback, final long timeout) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 单向模式发送消息
     */
    void sendOneway(final Message msg) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 同步发送消息、消息队列
     */
    SendResult send(final Message msg, final MessageQueue mq) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 同步发送消息、消息队列、超时时间
     */
    SendResult send(final Message msg, final MessageQueue mq, final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 同步发送消息、消息队列、回调函数
     */
    void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 同步发送消息、消息队列、回调函数、超时时间
     */
    void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback, long timeout) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 单向模式发送消息、消息队列
     */
    void sendOneway(final Message msg, final MessageQueue mq) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 同步发送消息、消息队列选择器、参数与消息队列选择器一起工作
     */
    SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 同步发送消息、消息队列选择器、参数与消息队列选择器一起工作、超时时间
     */
    SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg, final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 异步发送消息、消息队列选择器、参数与消息队列选择器一起工作、回调函数
     */
    void send(final Message msg, final MessageQueueSelector selector, final Object arg, final SendCallback sendCallback) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 异步发送消息、消息队列选择器、参数与消息队列选择器一起工作、回调函数、超时时间
     */
    void send(final Message msg, final MessageQueueSelector selector, final Object arg, final SendCallback sendCallback, final long timeout) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 单向模式发送消息、消息队列选择权、参数与消息队列选择器一起工作
     */
    void sendOneway(final Message msg, final MessageQueueSelector selector, final Object arg) throws MQClientException, RemotingException, InterruptedException;

    /**
     * 发送消息、本地事务执行器、与本地事务执行程序一起使用的参数
     */
    TransactionSendResult sendMessageInTransaction(final Message msg, final LocalTransactionExecuter tranExecuter, final Object arg) throws MQClientException;

    /**
     * 发送消息、与本地事务执行程序一起使用的参数
     */
    TransactionSendResult sendMessageInTransaction(final Message msg, final Object arg) throws MQClientException;

    //批量处理
    SendResult send(final Collection<Message> msgs) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    SendResult send(final Collection<Message> msgs, final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    SendResult send(final Collection<Message> msgs, final MessageQueue mq) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    SendResult send(final Collection<Message> msgs, final MessageQueue mq, final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException;
}
