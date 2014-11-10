package fr.atelechev.chess.fen2cb.style

import org.scalatest.FlatSpec
import org.scalatest.Matchers.be
import org.scalatest.Matchers.convertToAnyShouldWrapper

class StyleNameValidatorTestSuite extends FlatSpec {

  "isStyleNameValid(String)" should "return false for null arg" in {
    StyleNameValidator.isStyleNameValid(null) should be (false)
  }
  
  it should "return false for empty arg" in {
    StyleNameValidator.isStyleNameValid("   ") should be (false)
  }
  
  it should "return true for a valid name" in {
    StyleNameValidator.isStyleNameValid("default") should be (true)
  }
  
  it should "return false for a name with space" in {
    StyleNameValidator.isStyleNameValid("default style") should be (false)
  }
  
  it should "return false for a name with non alphanumeric char" in {
    StyleNameValidator.isStyleNameValid("default - style") should be (false)
  }
  
}