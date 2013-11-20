<?php
/**
 * This class perfom some queries on the database
 */
class DBHelper {
	
	private $dbFileName;
	
	function __construct($filename) {
		$this->dbFileName = $filename;
	}
	
	function findClothesByModel($modelName) {
		$db = new SQLite3($this->dbFileName);
		$data = $db->query(
			'SELECT model, image AS imageUrl, brand.brandName AS brand, color.colorName AS color, type.typeName AS type, bodies.bodiesName AS bodies 
			 FROM clothes 
			 JOIN brand ON clothes.ID_br=brand.ID_brand 
			 JOIN color ON clothes.ID_c=color.ID_color 
			 JOIN type ON clothes.ID_t=type.ID_type 
             JOIN bodies ON type.ID_b=bodies.ID_bodies 
			 WHERE model LIKE "%'. $modelName . '%"'
		);

		$arrayData = array();
        while ($row = $data->fetchArray(SQLITE3_ASSOC)) {
            $arrayData[] = $row;
        }

		$db->close();

   		return json_encode($arrayData);
	}
}

?>