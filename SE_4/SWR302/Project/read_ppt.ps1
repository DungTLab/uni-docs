# Script to extract slide text from the temp ppt slides
$slides = Get-ChildItem -Path "SWR302_Slides+Textbook/ch20_temp/ppt/slides/slide*.xml" | Sort-Object { [int]($_.BaseName -replace '\D') }

foreach ($slide in $slides) {
    Write-Output "--- Slide $($slide.BaseName) ---"
    [xml]$xml = Get-Content -Path $slide.FullName -Raw
    $ns = New-Object System.Xml.XmlNamespaceManager($xml.NameTable)
    $ns.AddNamespace("a", "http://schemas.openxmlformats.org/drawingml/2006/main")
    $texts = $xml.SelectNodes("//a:t", $ns)
    if ($texts) {
        $line = ($texts | ForEach-Object { $_.InnerText }) -join " "
        Write-Output $line
    }
}
