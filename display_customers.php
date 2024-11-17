<?php
include 'db_connection.php';

$sql = "SELECT * FROM CUSTOMER";
$result = $conn->query($sql);

echo "<h2>Customer Information</h2>";
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        echo "Customer ID: " . $row["CID"] . " - Name: " . $row["CNAME"] . "<br>";
    }
} else {
    echo "No customers found.";
}

$conn->close();
?>
