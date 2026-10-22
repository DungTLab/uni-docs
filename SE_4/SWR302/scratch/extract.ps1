$docPath = 'C:\Users\DungLT\.gemini\antigravity\brain\9bd06b73-1a94-4a93-8dee-53d37f96ea1b\.system_generated\steps\166\content.md'
$outputPath = 'd:\FPTU_document\SE_4\SWR302\scratch\uc_list.txt'

$lines = Get-Content -Path $docPath
$ucLines = @()

for ($i = 0; $i -lt $lines.Length; $i++) {
    $line = $lines[$i]
    # Match UC- followed by numbers, or lines that have "UC-" in them, between line 300 and 1300
    if ($i -ge 300 -and $i -le 1300 -and ($line -match "UC-")) {
        $ucLines += "Line $($i+1): $($line.Trim())"
    }
}

$ucLines | Out-File -FilePath $outputPath -Encoding utf8
Write-Host "Done! UC lines written to $outputPath"
