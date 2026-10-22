$docPath = 'C:\Users\DungLT\.gemini\antigravity\brain\9bd06b73-1a94-4a93-8dee-53d37f96ea1b\.system_generated\steps\115\content.md'
$outputPath = 'd:\FPTU_document\SE_4\SWR302\scratch\headings.txt'

$lines = Get-Content -Path $docPath
$headings = @()

for ($i = 0; $i -lt $lines.Length; $i++) {
    $line = $lines[$i].Trim()
    if ($line.Length -gt 0 -and ($line -match "^[#]+" -or $line -match "^\d+(\.\d+)*\s+[A-Z]" -or $line -match "Revision History" -or $line -match "Business Rules" -or $line -match "Use Cases" -or $line -match "Software Requirements Specification")) {
        $headings += "Line $($i+1): $line"
    }
}

$headings | Out-File -FilePath $outputPath -Encoding utf8
Write-Host "Done! Headings written to $outputPath"
