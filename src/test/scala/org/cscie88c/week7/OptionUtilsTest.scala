package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}
import scala.io.Source

class OptionUtilsTest extends StandardTest {
  "OptionUtils" when {
    "calling fileCharCount" should {
      "return the correct number of characters in a valid file" in {
        val fileName = "src/test/resources/data/dirty-retail-data-sample.csv"
        val expectedCharacterCount = 195
        val result = OptionUtils.fileCharCount(fileName)
        result shouldBe Success(expectedCharacterCount)
      }
    }
    "calling with a non-existent file" should {
      "return an error" in {
        val fileName = "src/test/resources/data/dirty-retail-data-sample.py"
        val result = OptionUtils.fileCharCount(fileName)
        result shouldBe a[Failure[_]]
      }
    }
    "calling charCountAsString" should {
      "return the correct string for a valid file" in {
        val fileName = "src/test/resources/data/dirty-retail-data-sample.csv"
        val expectedResult = "number of characters: 195"
        val result = OptionUtils.charCountAsString(fileName)
        result shouldBe expectedResult
      }
      "return an error string for a non-existent file" in {
        val fileName = "data/dirty-retail-data-sample.csv-x"
        val expectedResult = "error opening file"
        val result = OptionUtils.charCountAsString(fileName)
        result shouldBe expectedResult
      }
    }
  }

}
