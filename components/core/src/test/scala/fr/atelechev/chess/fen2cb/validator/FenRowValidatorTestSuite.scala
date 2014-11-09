package fr.atelechev.chess.fen2cb.validator

import fr.atelechev.util.ExpectedExceptionCatcher
import org.scalatest.FlatSpec
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.Matchers.be

class FenRowValidatorTestSuite extends FlatSpec 
								  with ExpectedExceptionCatcher {

  private val validator = FenRowValidator.getInstance()
  
  "isFenRowValid(String)" should "return false if the arg is null" in {
    validator.isFenRowValid(null) should be (false)
  }
  
  it should "return false if the arg is empty" in {
    validator.isFenRowValid("  ") should be (false)
  }
  
  it should "return false if total cells count is less than 8" in {
    validator.isFenRowValid("7") should be (false)
    validator.isFenRowValid("6p") should be (false)
    validator.isFenRowValid("4R1q") should be (false)
  }
  
  it should "return false if total cells count is greater than 8" in {
    validator.isFenRowValid("9") should be (false)
    validator.isFenRowValid("7pp") should be (false)
    validator.isFenRowValid("6Rp1") should be (false)
  }
  
  it should "return false if the value contains an illegal character" in {
    validator.isFenRowValid("1apppp2") should be (false)
  }
  
  it should "return true for a valid value" in {
    validator.isFenRowValid("8") should be (true)
    validator.isFenRowValid("1p6") should be (true)
    validator.isFenRowValid("2RR4") should be (true)
    validator.isFenRowValid("3n1NN1") should be (true)
    validator.isFenRowValid("4BBbb") should be (true)
    validator.isFenRowValid("5q2") should be (true)
    validator.isFenRowValid("6K1") should be (true)
    validator.isFenRowValid("7k") should be (true)
  }
  
}