# PowerShell script to extract text from docx files in the Project folder
$files = Get-ChildItem -Path "Project" -Filter "*.docx"

foreach ($file in $files) {
    $tempDir = Join-Path "Project" ($file.BaseName + "_temp")
    if (Test-Path $tempDir) {
        Remove-Item -Path $tempDir -Recurse -Force
    }
    
    # Copy to a temp zip file
    $tempZip = Join-Path "Project" ($file.BaseName + "_temp.zip")
    Copy-Item -Path $file.FullName -Destination $tempZip -Force
    
    # Extract zip archive
    Expand-Archive -Path $tempZip -DestinationPath $tempDir -Force
    
    # Clean up temp zip
    Remove-Item -Path $tempZip -Force
    
    $documentXmlPath = Join-Path $tempDir "word/document.xml"
    if (Test-Path $documentXmlPath) {
        # Read the XML and extract text nodes
        [xml]$xml = Get-Content -Path $documentXmlPath -Raw
        
        # XML namespace manager for word processing ML
        $ns = New-Object System.Xml.XmlNamespaceManager($xml.NameTable)
        $ns.AddNamespace("w", "http://schemas.openxmlformats.org/wordprocessingml/2006/main")
        
        # Select all paragraph elements and join their text
        $paragraphs = $xml.SelectNodes("//w:p", $ns)
        $textLines = foreach ($p in $paragraphs) {
            $texts = $p.SelectNodes(".//w:t", $ns)
            if ($texts) {
                $line = ($texts | ForEach-Object { $_.InnerText }) -join ""
                $line
            }
        }
        
        $outPath = Join-Path "Project" ($file.BaseName + "_text.txt")
        $textLines | Out-File -FilePath $outPath -Encoding utf8
    }
    
    # Cleanup temp dir
    Remove-Item -Path $tempDir -Recurse -Force
}
