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

	INSERT INTO application_role (id, name)
VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_ADMIN');

INSERT INTO application_user (id, user_name,password,application_role_id)
VALUES
	(1, 'trader1','traderpass',1),
	(2, 'trader2','traderpass',1),
	(3, 'broker1','brokerpass',2),
	(4, 'broker2','brokerpass',2);

		INSERT INTO application_user_role (id, application_user_id, application_role_id)
VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 3, 2),
	(4, 4, 2);

INSERT INTO trader (id, name,application_user_id)
VALUES
	(1, 'Safa Ertekin',1),
	(2, 'Merve Ertekin',2);

INSERT INTO broker (id, name,application_user_id)
VALUES
	(1, 'Hikmet Ertekin',3),
	(2, 'Hulya Ertekin',4);