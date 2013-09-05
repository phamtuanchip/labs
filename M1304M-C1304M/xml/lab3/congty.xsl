<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
  <html>
  <body>

<xsl:for-each select="NhanVien">
      <xsl:sort select="HoTen"/>
       <P>
        <font color="blue" size="6">
          <xsl:value-of select="HoTen"/>
          ,
          <xsl:value-of select="DiaChi"/>
        </font>
       </P>
    </xsl:for-each>
 
 </body>
  </html>
</xsl:template>

</xsl:stylesheet>