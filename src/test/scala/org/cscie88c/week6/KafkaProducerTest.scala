package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class KafkaProducerTest extends StandardTest {
  "KafkaClient" should {
    "send a message to the default topic" in {
      // unit tests for KafkaClient.send
      val status = "Testing KafkaClient"
      val response = KafkaClient.sendStatusEvent(status)
      response should include("default-topic")

    }
  }
}
