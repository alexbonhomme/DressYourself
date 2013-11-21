<?php
/**
 * This class perfom some queries on the database
 */
class DBHelper {
	
	private $dbFileName;
	
	function __construct($filename) {
		$this->dbFileName = $filename;
	}
	
	private function SQLite2JSON($result) {
		$arrayData = array();
        while ($row = $result->fetchArray(SQLITE3_ASSOC)) {
            $arrayData[] = $row;
        }
		
		return $arrayData;
	}
	
	function findClothesByModel($modelName) {
		$db = new SQLite3($this->dbFileName);
		$data = $db->query(
			'SELECT ID_clothes AS id, model, image AS imageUrl, brand.brandName AS brand, color.colorName AS color, type.typeName AS type, bodies.bodiesName AS bodies 
			 FROM clothes 
			 JOIN brand ON clothes.ID_br=brand.ID_brand 
			 JOIN color ON clothes.ID_c=color.ID_color 
			 JOIN type ON clothes.ID_t=type.ID_type 
             JOIN bodies ON type.ID_b=bodies.ID_bodies 
			 WHERE model LIKE "%'. $modelName . '%"'
		);

		$arrayData = $this->SQLite2JSON($data);

		$db->close();

   		return json_encode($arrayData);
	}
	
	function findClotheById($id) {
		$db = new SQLite3($this->dbFileName);
		$data = $db->query(
			'SELECT ID_clothes AS id, model, image AS imageUrl, brand.brandName AS brand, color.colorName AS color, type.typeName AS type, bodies.bodiesName AS bodies 
			 FROM clothes 
			 JOIN brand ON clothes.ID_br=brand.ID_brand 
			 JOIN color ON clothes.ID_c=color.ID_color 
			 JOIN type ON clothes.ID_t=type.ID_type 
             JOIN bodies ON type.ID_b=bodies.ID_bodies 
			 WHERE ID_clothes = '. $id
		);

		$arrayData = $this->SQLite2JSON($data);

		$db->close();

   		return json_encode($arrayData);
	}
}

?>