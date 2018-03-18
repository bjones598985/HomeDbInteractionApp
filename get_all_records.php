<?php

$response = array();

require_once 'Config.php';

$conn = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    // Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$result = $conn->query("Select * from Data");
if ($result->num_rows > 0) {
    $response["records"] = array();
    
    while ($row = $result->fetch_assoc()) {
    
        $record = array();
        $record["id"] = $row["_id"];
        $record["sequence"] = $row["sequence"];
        $record["time"] = $row["time"];
        $record["distance"] = $row["distance"];
        $record["calories"] = $row["calories"];
        $record["cardio_type"] = $row["cardio_type"];
    
        array_push($response["records"], $record);
    }
    
    $response["success"] = 1;
    
    echo json_encode($response);
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Records Found";
    
    echo json_encode($response);
}
$conn->close();

?>