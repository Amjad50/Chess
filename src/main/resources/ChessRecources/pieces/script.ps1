$svgs = ls -Name | Where-Object {$_.ToString() -like "*.svg*" }
echo $svgs
$newSVGS = $svgs -replace '.svg',''

echo $newSVGS
echo $svgs

$svgs |ForEach-Object {
    java -jar .\svg2fxml-0.8.1-SNAPSHOT.jar $_.ToString() ($_.ToString() -replace '.svg', '.fxml')
}