package org.cscie88c.week6

// define trait KafkaProducer 
trait KafkaProducer {
  def send(message: String): String
}

// define companion object KafkaProducer

object KafkaProducer {
  implicit val defaultKafkaProducer: SimpleKafkaProducer = SimpleKafkaProducer("default-topic")
}

// define the case class SimpleKafkaProducer below
case class SimpleKafkaProducer(topic: String) extends KafkaProducer {
  def send(message: String): String = s"Message '$message' sent to topic '$topic'"
}

// define KafkaClient object
object KafkaClient {
  // sends a status message to kafka
  def sendStatusEvent(status: String)(implicit kafkaProducer: KafkaProducer) = {
    kafkaProducer.send(status) // use the implicit KafkaProducer provided
  }  
}

