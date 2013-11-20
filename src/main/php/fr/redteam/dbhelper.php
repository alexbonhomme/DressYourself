<?php
/**
 * This class perfom some queries on the database
 */
class DBHelper {
	
	private $dbFileName;
	
	function __construct($filename) {
		$this->dbFileName = 'zara_20131120.sqlite';
	}
	
	function findClothesByModel($modelName) {
		$db = new SQLite3($this->dbFileName);
		$data = $db->query('SELECT * FROM clothes WHERE model LIKE "'. $modelName . '"');

		$arrayData = array();
        while ($row = $data->fetchArray(SQLITE3_ASSOC)) {
            $arrayData[] = $row;
        }

		$db->close();

   		return json_encode($arrayData);
	}
}

?>