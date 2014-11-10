package fr.atelechev.chess.fen2cb.style

import org.scalatest.FlatSpec
import org.scalatest.Matchers.be
import org.scalatest.Matchers.not
import org.scalatest.Matchers.convertToAnyShouldWrapper
import fr.atelechev.util.ExpectedExceptionCatcher
import java.nio.file.Paths
import java.awt.Point

class DiagramStyleFactoryTestSuite extends FlatSpec
									  with ExpectedExceptionCatcher
									  with StyleFolderAccessor {
  
  private val testResourcesPath = getFolderForStyle(null)

  "buildStyle(String, Path)" should "throw IllegalArgumentException if the String arg is null" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      DiagramStyleFactory.buildStyle(null, testResourcesPath);
    })
  }
  
  it should "throw IllegalArgumentException if the String arg is empty" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      DiagramStyleFactory.buildStyle("   ", testResourcesPath);
    })
  }
  
  it should "throw IllegalArgumentException if the style name does not respect the naming convention" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      DiagramStyleFactory.buildStyle("- what a style! :)", testResourcesPath);
    })
  }
  
  it should "throw IllegalArgumentException if the style data folder does not exist" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      DiagramStyleFactory.buildStyle("inexistent", testResourcesPath);
    })
  }
  
  it should "throw IllegalArgumentException if the style name refers to a file" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      DiagramStyleFactory.buildStyle("not_a_folder", testResourcesPath);
    })
  }
  
  it should "throw IllegalStateException if the diagram.properties file was not found" in {
    catchExpected(classOf[IllegalStateException], () => {
      DiagramStyleFactory.buildStyle("no_properties", testResourcesPath);
    })
  }
  
  it should "throw UnsupportedOperationException if render.type is not 'raster' in diagram.properties" in {
    catchExpected(classOf[UnsupportedOperationException], () => {
      DiagramStyleFactory.buildStyle("custom_properties", testResourcesPath);
    })
  }
  
  it should "return a RasterStyle for 'default' style name" in {
    val style = DiagramStyleFactory.buildStyle("default", testResourcesPath);
    style should not be (null)
    style.getClass should be (classOf[RasterStyle])
    val rasterStyle = style.asInstanceOf[RasterStyle]
    rasterStyle.getName should be ("default")
    rasterStyle.getCellsOrigin should be (new Point(0, 0))
    rasterStyle.getOverlayOrigin should be (null)
    rasterStyle.getBoard should not be (null)
    Piece.values.foreach(piece => {
      rasterStyle.getPiece(piece) should not be (null)
    })
  }
  
}