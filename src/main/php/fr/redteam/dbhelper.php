<?php
/**
 * This class performs some queries on the database
 */
class DBHelper {
	
	const QUERY_BASE = 
		'SELECT ID_clothes AS id, model, imageUrl, imagePath, brand.brandName AS brand, color.colorName AS color, type.typeName AS type, bodies.bodiesName AS bodies 
		 FROM clothes 
		 JOIN brand ON clothes.ID_br=brand.ID_brand 
		 JOIN color ON clothes.ID_c=color.ID_color 
		 JOIN type ON clothes.ID_t=type.ID_type 
         JOIN bodies ON type.ID_b=bodies.ID_bodies';
	
	private $dbFileName;
	private $filters;
	
	function __construct($filename) {
		$this->dbFileName = $filename;
		$this->filters = array();
	}
	
	public function addFilter($filter) {
		$this->filters[] = $filter;
	}
	
	public function addFilters($filters) {
		$this->filters += $filters;
	}
	
	private function SQLite2JSON($result) {
		$arrayData = array();
        while ($row = $result->fetchArray(SQLITE3_ASSOC)) {
            $arrayData[] = $row;
        }
		
		return $arrayData;
	}
	
	public function findAll($query)	{
		if (empty($this->filters)) {
			return json_encode(array());
		}
		
		$sqlQuery = self::QUERY_BASE .' WHERE 0';

		foreach ($this->filters as $filter) {
			$sqlQuery .= ' OR ' . $filter . ' LIKE "%' . $query . '%"';
		}
		
		$db = new SQLite3($this->dbFileName);
		$data = $db->query($sqlQuery);
		$arrayData = $this->SQLite2JSON($data);

		$db->close();

   		return json_encode($arrayData);
	}
	
	public function findClothesByModel($modelName) {
		$db = new SQLite3($this->dbFileName);
		
		$data = $db->query(self::QUERY_BASE .' WHERE model LIKE "%'. $modelName .'%"');
		$arrayData = $this->SQLite2JSON($data);

		$db->close();

   		return json_encode($arrayData);
	}
	
	public function findClotheById($id) {
		$db = new SQLite3($this->dbFileName);
		
		$data = $db->query(self::QUERY_BASE .' WHERE ID_clothes = '. $id);
		$arrayData = $this->SQLite2JSON($data);

		$db->close();

   		return json_encode($arrayData);
	}
}

?>