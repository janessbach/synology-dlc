package utils

import com.googlecode.htmlcompressor.compressor.HtmlCompressor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait TestHtml {

  def compressed(implicit compressor: HtmlCompressor = htmlCompressor): String =
    compressor.compress(normalized)

  private def htmlCompressor: HtmlCompressor = {
    val c = new HtmlCompressor
    c.setCompressJavaScript(true)
    c
  }

  def normalized: String = parsed.html

  def parsed: Document = Jsoup.parse(text)

  def text: String

}