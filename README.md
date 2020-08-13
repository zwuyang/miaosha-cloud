# miaosha-cloud
使用SpringCloudAlibaba来实现秒杀


#### 关于服务拆分
暂时拆分为 用户中心 秒杀服务生产者  秒杀服务消费者
用户中心是否为生产者提供服务？
考虑抽取common作为一个服务，以及抽取service层。