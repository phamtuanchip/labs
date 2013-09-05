<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="ChaoHoi">
  <html>
     <body>
	<xsl:text>Su dung apply-templates</xsl:text>
       <h1> <xsl:apply-templates select="Chao"/></h1>
       <font size="36" color="blue">
       <xsl:value-of select = "Hoi"/>
       </font>
     </body>
  </html>
 </xsl:template>
</xsl:stylesheet>