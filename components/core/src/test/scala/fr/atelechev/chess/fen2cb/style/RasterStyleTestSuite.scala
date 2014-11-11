package fr.atelechev.chess.fen2cb.style

import org.scalatest.FlatSpec
import org.scalatest.Matchers.be
import org.scalatest.Matchers.convertToAnyShouldWrapper
import java.awt.image.BufferedImage

class RasterStyleTestSuite extends FlatSpec
							  with StyleFolderAccessor {

  private val testResourcesPath = getFolderForStyle(null)
  
  private def buildStyle(styleName : String, overlay : Point) = {
    val styleFolder = testResourcesPath.resolve(styleName)
    RasterStyleFactory.buildStyle(styleFolder, styleName, new Point(0, 0), overlay)
  }
  
  "shouldDrawOverlay()" should "return true if overlay origin and image are set" in {
    val style = buildStyle("default", new Point(100, 100))
    style.setOverlayImage(new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB))
    style.shouldDrawOverlay should be (true)
  }
  
  it should "return false if overlay origin is set but the image is not set" in {
    val style = buildStyle("default", new Point(100, 100))
    style.setOverlayImage(null)
    style.shouldDrawOverlay should be (false)
  }
  
  it should "return false if overlay origin is not set but the image is set" in {
    val style = buildStyle("default", null)
    style.setOverlayImage(new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB))
    style.shouldDrawOverlay should be (false)
  }
  
}