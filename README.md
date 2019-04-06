#### main

##### Transfer Data From Kafka to HBase

执行me.icocoro.quickstart.streaming.StreamKafkaProducer.main#
和me.icocoro.quickstart.streaming.KafkaToHBaseJob.main#

观察控制台输出(略)

```bash
$ hbase shell
hbase(main):001:0> list
TABLE                                                                                                                                                                     
trade_pay_info                                                                                                                                                            
webtable                                                                                                                                                                  
2 row(s)
Took 0.4359 seconds                                                                                                                                                       
=> ["trade_pay_info", "webtable"]
hbase(main):003:0> scan 'trade_pay_info' 
dateformat9e4856599-4cf7-477d-a69f-255a4f1 column=baseinfo:timestamp, timestamp=1554371824958, value=1554371821731                                                       
 07132                                                                                                                                                                    
 dateformat9e4856599-4cf7-477d-a69f-255a4f1 column=baseinfo:total_amount, timestamp=1554371824881, value=80.18125799960907                                                
 07132                                                                                                                                                                    
 dateformat9e4856599-4cf7-477d-a69f-255a4f1 column=baseinfo:trade_no, timestamp=1554371824710, value=t9                                                                   
 07132                                                                                                                                                                    
 dateformat9e4856599-4cf7-477d-a69f-255a4f1 column=baseinfo:trade_pay_id, timestamp=1554371824666, value=9e4856599-4cf7-477d-a69f-255a4f107132                            
 07132                                                                                                                                                                    
 dateformat9e4856599-4cf7-477d-a69f-255a4f1 column=baseinfo:trade_type, timestamp=1554371824794, value=T9                                                                 
 07132                                                                                                                                                                    
200 row(s)
Took 1.1644 seconds  
```

#### test

##### HBase

```bash
$ start-dfs.sh 
Starting namenodes on [localhost]
Starting datanodes
Starting secondary namenodes [localhost]

$ zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /Users/shaozhipeng/Development/zookeeper-3.4.12/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED

$ start-hbase.sh
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/shaozhipeng/Development/hadoop-3.1.1/share/hadoop/common/lib/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/shaozhipeng/Development/hbase-2.1.0/lib/client-facing-thirdparty/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
running master, logging to /Users/shaozhipeng/Development/hbase-2.1.0/logs/hbase-shaozhipeng-master-localhost.out
: running regionserver, logging to /Users/shaozhipeng/Development/hbase-2.1.0/logs/hbase-shaozhipeng-regionserver-localhost.out
localhost: master running as process 4585. Stop it first.

$ jps
3760 SecondaryNameNode
4722 HRegionServer
3525 NameNode
4585 HMaster
3626 DataNode
4429 QuorumPeerMain
```

执行me.icocoro.hbase.HBaseClientTest.main#

```bash
22:30:40,127 INFO  org.apache.hadoop.hbase.client.HBaseAdmin                     - Created webtable
create table Success!
add data Success!
result.current(): null
cellScanner: keyvalues={https://icocoro.me/contents:html/1554359440303/Put/vlen=71/seqid=0}
cellScanner.current(): null
---------------------------------------------
cell: https://icocoro.me/contents:html/1554359440303/Put/vlen=71/seqid=0
family: contents
qualifier: html
value: webtable-https://icocoro.me-contents:html=<html><head></head>...</html>
timestamp: 1554359440303
---------------------------------------------
```

hbase shell

```bash
$ hbase shell
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/shaozhipeng/Development/hadoop-3.1.1/share/hadoop/common/lib/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/shaozhipeng/Development/hbase-2.1.0/lib/client-facing-thirdparty/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
2019-04-03 22:27:13,173 WARN  [main] util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
HBase Shell
Use "help" to get list of supported commands.
Use "exit" to quit this interactive shell.
Version 2.1.0, re1673bb0bbfea21d6e5dba73e013b09b8b49b89b, Tue Jul 10 17:26:48 CST 2018
Took 0.0222 seconds                                                                                                                                                       
hbase(main):004:0> list
TABLE                                                                                                                                                                     
webtable                                                                                                                                                                  
1 row(s)
Took 0.0499 seconds                                                                                                                                                       
=> ["webtable"]
```

##### Kafka

```bash
$ cd /Users/shaozhipeng/Development/kafka_2.11-0.11.0.3
$ kafka-server-start.sh -daemon config/server.properties

$ jps
5410 Kafka

$ lsof -i:9092
COMMAND  PID        USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
java    5410 shaozhipeng  159u  IPv6 0x9ebe9d8a1c4ad1bb      0t0  TCP localhost:XmlIpcRegSvc (LISTEN)
java    5410 shaozhipeng  166u  IPv6 0x9ebe9d8a19173bfb      0t0  TCP localhost:61871->localhost:XmlIpcRegSvc (ESTABLISHED)
java    5410 shaozhipeng  167u  IPv6 0x9ebe9d8a191741bb      0t0  TCP localhost:XmlIpcRegSvc->localhost:61871 (ESTABLISHED)

$ kafka-topics.sh --list --zookeeper localhost:2181
__consumer_offsets
test
```

执行me.icocoro.kafka.KafkaProducerTest.main#

```bash
23:02:29,011 INFO  org.apache.kafka.common.utils.AppInfoParser                   - Kafka version : 0.11.0.2
23:02:29,011 INFO  org.apache.kafka.common.utils.AppInfoParser                   - Kafka commitId : 73be1e1168f91ee2
```

执行me.icocoro.kafka.KafkaConsumerTest.main#

```bash
23:02:07,719 INFO  org.apache.kafka.clients.consumer.internals.AbstractCoordinator  - Discovered coordinator localhost:9092 (id: 2147483647 rack: null) for group test-group.
23:02:07,723 INFO  org.apache.kafka.clients.consumer.internals.ConsumerCoordinator  - Revoking previously assigned partitions [] for group test-group
23:02:07,723 INFO  org.apache.kafka.clients.consumer.internals.AbstractCoordinator  - (Re-)joining group test-group
23:02:11,008 INFO  org.apache.kafka.clients.consumer.internals.AbstractCoordinator  - Successfully joined group test-group with generation 4
23:02:11,010 INFO  org.apache.kafka.clients.consumer.internals.ConsumerCoordinator  - Setting newly assigned partitions [my-topic-0] for group test-group
my-topic-0-0-1554361349340
my-topic-0-1-1554361349391
my-topic-0-2-1554361349391
my-topic-0-3-1554361349391
my-topic-0-4-1554361349391
my-topic-0-5-1554361349391
my-topic-0-6-1554361349391
my-topic-0-7-1554361349391
my-topic-0-8-1554361349391
my-topic-0-9-1554361349391
my-topic-0-10-1554361349391
my-topic-0-11-1554361349391
my-topic-0-12-1554361349392
my-topic-0-13-1554361349392
my-topic-0-14-1554361349392
my-topic-0-15-1554361349392
my-topic-0-16-1554361349392
my-topic-0-17-1554361349392
my-topic-0-18-1554361349392
my-topic-0-19-1554361349392
my-topic-0-20-1554361349392
my-topic-0-21-1554361349392
my-topic-0-22-1554361349392
my-topic-0-23-1554361349392
my-topic-0-24-1554361349392
my-topic-0-25-1554361349392
my-topic-0-26-1554361349393
my-topic-0-27-1554361349393
my-topic-0-28-1554361349393
my-topic-0-29-1554361349393
my-topic-0-30-1554361349393
my-topic-0-31-1554361349393
my-topic-0-32-1554361349393
my-topic-0-33-1554361349393
my-topic-0-34-1554361349393
my-topic-0-35-1554361349393
my-topic-0-36-1554361349393
my-topic-0-37-1554361349393
my-topic-0-38-1554361349393
my-topic-0-39-1554361349393
my-topic-0-40-1554361349393
my-topic-0-41-1554361349393
my-topic-0-42-1554361349394
my-topic-0-43-1554361349394
my-topic-0-44-1554361349394
my-topic-0-45-1554361349394
my-topic-0-46-1554361349394
my-topic-0-47-1554361349394
my-topic-0-48-1554361349394
my-topic-0-49-1554361349394
my-topic-0-50-1554361349394
my-topic-0-51-1554361349394
my-topic-0-52-1554361349395
my-topic-0-53-1554361349395
my-topic-0-54-1554361349395
my-topic-0-55-1554361349395
my-topic-0-56-1554361349395
my-topic-0-57-1554361349395
my-topic-0-58-1554361349395
my-topic-0-59-1554361349395
my-topic-0-60-1554361349395
my-topic-0-61-1554361349395
my-topic-0-62-1554361349395
my-topic-0-63-1554361349395
my-topic-0-64-1554361349395
my-topic-0-65-1554361349395
my-topic-0-66-1554361349396
my-topic-0-67-1554361349396
my-topic-0-68-1554361349396
my-topic-0-69-1554361349396
my-topic-0-70-1554361349396
my-topic-0-71-1554361349396
my-topic-0-72-1554361349396
my-topic-0-73-1554361349396
my-topic-0-74-1554361349396
my-topic-0-75-1554361349396
my-topic-0-76-1554361349396
my-topic-0-77-1554361349396
my-topic-0-78-1554361349396
my-topic-0-79-1554361349396
my-topic-0-80-1554361349396
my-topic-0-81-1554361349396
my-topic-0-82-1554361349396
my-topic-0-83-1554361349396
my-topic-0-84-1554361349397
my-topic-0-85-1554361349397
my-topic-0-86-1554361349397
my-topic-0-87-1554361349397
my-topic-0-88-1554361349397
my-topic-0-89-1554361349397
my-topic-0-90-1554361349397
my-topic-0-91-1554361349397
my-topic-0-92-1554361349397
my-topic-0-93-1554361349397
my-topic-0-94-1554361349397
my-topic-0-95-1554361349397
my-topic-0-96-1554361349397
my-topic-0-97-1554361349397
my-topic-0-98-1554361349397
my-topic-0-99-1554361349397
```

##### RocketMQ

Producer：SyncProducer AsyncProducer OnewayProducer  
Consumer：DefaultMQPushConsumer，DefaultMQPullConsumer


##### GitHub

git init

git add src  
git add pom.xml  
git add README.md  

git commit -m 'flink-quickstart first commit'  
git remote add origin https://github.com/shaozhipeng/flink-quickstart.git  
git push -u origin master 