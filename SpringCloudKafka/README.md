# Binder abstraction

Spring Cloud Stream используется абстрацию Binder для абстрагирования от конкретного источника сообщений.

Сейчас - 27/06/2022 - из коробки поддерживаются два Binder-а: [Kafka](https://github.com/spring-cloud/spring-cloud-stream-binder-kafka) и [RabbitMQ](https://github.com/spring-cloud/spring-cloud-stream-binder-rabbit)

Нас здесь интересует [Kafka](https://github.com/spring-cloud/spring-cloud-stream-binder-kafka)

# Kafka Binder

Spring Cloud Stream создает абстрацию для:
- destination
- partition
- consumer group

Binder для Apache Kafka проводит взаимнооднозначное соответствие между этими абстрациями в Spring Cloud Stream и в Apache Kafka.

То есть:
- каждый destination мапится на отдельный kafka topic
- Механизм партициронирования в spring cloud stream прямо мапится на партиции топиков в apache kafka
- Аналогично для consumer groups

