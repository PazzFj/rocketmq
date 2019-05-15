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
package org.apache.rocketmq.client.impl.consumer;

import java.util.Set;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.body.ConsumerRunningInfo;
import org.apache.rocketmq.common.protocol.heartbeat.ConsumeType;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.common.protocol.heartbeat.SubscriptionData;

/**
 * 消费者内部的接口
 */
public interface MQConsumerInner {
    /**
     * 获取组名
     */
    String groupName();

    /**
     * 消息类型(广播、集群)
     */
    MessageModel messageModel();

    /**
     * 消费类型(PULL、 PUSH)
     */
    ConsumeType consumeType();

    /**
     * 消费起始位置(头部消费、尾部消费)
     */
    ConsumeFromWhere consumeFromWhere();

    /**
     * 订阅
     */
    Set<SubscriptionData> subscriptions();

    /**
     * 做调整(平衡)
     */
    void doRebalance();

    /**
     * 保存消费记录
     */
    void persistConsumerOffset();

    /**
     * 修改topic 订阅信息
     */
    void updateTopicSubscribeInfo(final String topic, final Set<MessageQueue> info);

    /**
     * 获取消费运行信息
     */
    ConsumerRunningInfo consumerRunningInfo();

    boolean isSubscribeTopicNeedUpdate(final String topic);

    boolean isUnitMode();
}
