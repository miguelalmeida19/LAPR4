<?php
$outputString = $_GET["text"];
$file = 'survey_answered.txt';
$data = 'cheirete';
file_put_contents($file, $outputString);
?>