INSERT INTO security (id, symbol, price, spread)
VALUES
	(1, 'GARAN.E', round((rand()*100), 2), round(rand(),2)),
	(2, 'AKBNK.E', round((rand()*100), 2), round(rand(),2)),
	(3, 'VAKBN.E', round((rand()*100), 2), round(rand(),2)),
	(4, 'SAHOL.E', round((rand()*100), 2), round(rand(),2)),
	(5, 'TCELL.E', round((rand()*100), 2), round(rand(),2)),
	(6, 'PGSUS.E', round((rand()*100), 2), round(rand(),2)),
	(7, 'TUPRS.E', round((rand()*100), 2), round(rand(),2)),
	(8, 'NETAS.E', round((rand()*100), 2), round(rand(),2)),
	(9, 'TSPOR.E', round((rand()*100), 2), round(rand(),2)),
	(10, 'BJKAS.E', round((rand()*100), 2), round(rand(),2));

INSERT INTO trader (id, name)
VALUES
	(1, 'Safa Ertekin');
