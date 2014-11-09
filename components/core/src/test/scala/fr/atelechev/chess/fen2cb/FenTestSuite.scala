package fr.atelechev.chess.fen2cb

import fr.atelechev.util.ExpectedExceptionCatcher
import org.scalatest.FlatSpec
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.Matchers.be
import fr.atelechev.chess.fen2cb.exception.IllegalFenException
import java.util.Arrays
import scala.collection.JavaConverters._

class FenTestSuite extends FlatSpec 
					  with ExpectedExceptionCatcher {

  private val startPos = List[List[Char]](
      List('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'),
      List('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
      List('8'),
      List('8'),
      List('8'),
      List('8'),
      List('P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
      List('R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R')
  )
  
  "fromPath(String)" should "throw IllegalArgumentException if the arg is null" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      Fen.fromPath(null)
    })
  }
  
  it should "throw IllegalArgumentException if the arg is empty" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      Fen.fromPath("   ")
    })
  }
  
  it should "throw IllegalFenException if the arg contains a FEN string with invalid chars" in {
    catchExpected(classOf[IllegalFenException], () => {
      Fen.fromPath("8/1p6/abc5/8/8/8/8/8")
    })
  }
  
  it should "throw IllegalFenException if the arg contains an incomplete FEN string" in {
    catchExpected(classOf[IllegalFenException], () => {
      Fen.fromPath("rnbqkbnr/pppppppp/8/8/8/8")
    })
  }
  
  it should "return a filled Fen object for valid FEN string" in {
    val fen = Fen.fromPath("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
    val rows = Arrays.asList(fen.getRows).asScala.toList
    (fen.getRows, startPos).zipped.foreach((produced, expected) => {
      produced should be (expected)
    })
  }
  
  it should "set side to White by default" in {
    val fen = Fen.fromPath("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
    fen.getSide should be (Side.WHITE)
  }
  
  it should "set side to Black is required" in {
    val fen = Fen.fromPath("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/b")
    fen.getSide should be (Side.BLACK)
  }
  
}