package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "load and clean raw CSV data file" in {
      val fileName = "src/test/resources/data/dirty-retail-data-sample.csv"
      val result = CustomerTransaction.readFromCSVFile(fileName)
      result.length shouldBe 5
    }
    "return None for an invalid CSV string" in {
      val invalidCsvString = "id1,2023-10-26,90z"
      val expectedResult = None
      val result = CustomerTransaction(invalidCsvString)
      result shouldBe expectedResult
    }
    "correctly parse a valid CSV string into a CustomerTransaction object" in {
      val validCsvString = "id1,2023-10-26,84.5"
      val expectedResult = Some(CustomerTransaction("id1", "2023-10-26", 84.5))
      val result = CustomerTransaction(validCsvString)
      result shouldBe expectedResult
    }
  }
  "CustomerTransaction.apply" should {
    "return Some(CustomerTransaction) for valid input" in {
      val csvString = "Cust123,2023-10-25,100.0"
      val result = CustomerTransaction.apply(csvString)
      result shouldBe Some(CustomerTransaction("Cust123", "2023-10-25", 100.0))
    }
    "return None for invalid input" in {
      val csvString = "InvalidInput"
      val result = CustomerTransaction.apply(csvString)
      result shouldBe None
    }
  }
}
