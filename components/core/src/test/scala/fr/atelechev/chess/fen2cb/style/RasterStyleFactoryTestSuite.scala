package fr.atelechev.chess.fen2cb.style

import org.scalatest.FlatSpec
import fr.atelechev.util.ExpectedExceptionCatcher

class RasterStyleFactoryTestSuite extends FlatSpec
									 with ExpectedExceptionCatcher
									 with StyleFolderAccessor {

  private val testResourcesPath = getFolderForStyle(null)
  
  "buildStyle(Path, String, Point, Point)" should "throw IllegalStateException if all pieces images do not have same dimensions" in {
    val styleName = "invalid_piece"
    val styleFolder = testResourcesPath.resolve(styleName)
    catchExpected(classOf[IllegalStateException], () => {
      RasterStyleFactory.buildStyle(styleFolder, styleName, new Point(0, 0), null)
    })
  }
  
  it should "throw IllegalStateException if a piece image is missing" in {
    val styleName = "missing_piece"
    val styleFolder = testResourcesPath.resolve(styleName)
    catchExpected(classOf[IllegalStateException], () => {
      RasterStyleFactory.buildStyle(styleFolder, styleName, new Point(0, 0), null)
    })
  }
  
}