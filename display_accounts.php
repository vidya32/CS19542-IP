<?php
include 'db_connection.php';

$sql = "SELECT ACCOUNT.ANO, ACCOUNT.ATYPE, ACCOUNT.BALANCE, CUSTOMER.CNAME 
        FROM ACCOUNT 
        JOIN CUSTOMER ON ACCOUNT.CID = CUSTOMER.CID";
$result = $conn->query($sql);

echo "<h2>Account Information</h2>";
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        echo "Account Number: " . $row["ANO"] . " - Type: " . $row["ATYPE"] . 
             " - Balance: " . $row["BALANCE"] . " - Customer Name: " . $row["CNAME"] . "<br>";
    }
} else {
    echo "No accounts found.";
}

$conn->close();
?>
