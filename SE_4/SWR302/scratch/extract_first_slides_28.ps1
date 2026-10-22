$slides = Get-ChildItem "D:\FPTU_document\SE_4\SWR302\scratch\extracted_ppt_28\ppt\slides\slide*.xml"
$sorted = $slides | Sort-Object { [int]($_.BaseName -replace '\D') }

for ($i = 0; $i -lt 13; $i++) {
    $slide = $sorted[$i]
    if ($slide -ne $null) {
        [xml]$xml = Get-Content $slide.FullName
        $namespaces = New-Object System.Xml.XmlNamespaceManager($xml.NameTable)
        $namespaces.AddNamespace("a", "http://schemas.openxmlformats.org/drawingml/2006/main")
        $textNodes = $xml.SelectNodes("//a:t", $namespaces)
        $texts = @()
        foreach ($node in $textNodes) {
            $texts += $node.InnerText
        }
        Write-Output "=== $($slide.BaseName) ==="
        Write-Output ($texts -join " | ")
        Write-Output ""
    }
}
