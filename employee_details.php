<?php
include 'config.php';

$sql = "SELECT * FROM EMPDETAILS";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<table border='1'>
          <tr>
              <th>EMPID</th>
              <th>ENAME</th>
              <th>DESIG</th>
              <th>DEPT</th>
              <th>DOJ</th>
              <th>SALARY</th>
          </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr>
                  <td>" . $row["EMPID"] . "</td>
                  <td>" . $row["ENAME"] . "</td>
                  <td>" . $row["DESIG"] . "</td>
                  <td>" . $row["DEPT"] . "</td>
                  <td>" . $row["DOJ"] . "</td>
                  <td>" . $row["SALARY"] . "</td>
              </tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}
$conn->close();
?>
