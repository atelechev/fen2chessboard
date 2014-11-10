package fr.atelechev.chess.fen2cb.style

import org.scalatest.FlatSpec
import org.scalatest.Matchers.be
import org.scalatest.Matchers.convertToAnyShouldWrapper
import java.nio.file.Paths
import fr.atelechev.util.ExpectedExceptionCatcher

class DiagramPropertiesTestSuite extends FlatSpec
									with ExpectedExceptionCatcher
									with StyleFolderAccessor {

  "constructor()" should "fill the properties with default values" in {
    val props = new DiagramProperties
    props.get(DiagramProperty.RENDER_TYPE) should be ("raster");
    props.get(DiagramProperty.OVERLAY_X) should be ("");
    props.get(DiagramProperty.OVERLAY_Y) should be ("");
    props.get(DiagramProperty.PADDING_TOP) should be ("0");
    props.get(DiagramProperty.PADDING_LEFT) should be ("0");
  }
  
  "constructor(Path)" should "read the properties from the specified file" in {
    val file = getFolderForStyle("custom_properties").resolve("diagram.properties")
    val props = new DiagramProperties(file)
    props.get(DiagramProperty.RENDER_TYPE) should be ("custom");
    props.get(DiagramProperty.OVERLAY_X) should be ("300");
    props.get(DiagramProperty.OVERLAY_Y) should be ("200");
    props.get(DiagramProperty.PADDING_TOP) should be ("10");
    props.get(DiagramProperty.PADDING_LEFT) should be ("100");
  }
  
  it should "throw IllegalArgumentException if the arg is null" in {
    catchExpected(classOf[IllegalArgumentException], () => {
      new DiagramProperties(null)
    })
  }
  
  it should "throw IllegalStateException if the fiel of the does not exist" in {
    catchExpected(classOf[IllegalStateException], () => {
      new DiagramProperties(getFolderForStyle("custom_properties").resolve("inexistent"))
    })
  }
  
}