$slides = Get-ChildItem "D:\FPTU_document\SE_4\SWR302\scratch\extracted_ppt_30\ppt\slides\slide*.xml"
# Sort them numerically
$sorted = $slides | Sort-Object { [int]($_.BaseName -replace '\D') }

foreach ($slide in $sorted) {
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
