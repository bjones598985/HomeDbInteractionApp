<?php

require_once 'Config.php';

$conn = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    // Check connection
//echo "database connected </br>";
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
    echo "Connection failed: " . $conn->connect_error;
}

$date = $_POST['date'];
$sequence = $_POST['sequence'];
$time = $_POST['time'];
$distance = $_POST['distance'];
$calories = $_POST['calories'];
$cardioType = $_POST['cardio_type'];
$notes = $_POST['notes'];

if (strlen($notes) == 0) {
    $notes = null;
} 

$stmt = $conn->prepare("INSERT INTO Data (date, sequence, time, distance, calories, cardio_type, notes) VALUES (?, ?, ?, ?, ?, ?, ?)");
$response = array();
if ($stmt != false) {
    $stmt->bind_param("siidiss", $date, $sequence, $time, $distance, $calories, $cardioType, $notes);//siidiss is the types of variable being bound
    $stmt->execute();
    $response["result"] = "Successful";
    echo json_encode($response);
} else {
    $response["result"] = "Failed";
    echo json_encode($response);
}
?>