insert into product(id, productName, description, unitPrice, active, version) values(1,'Product 1', 'Description 1', 5.00, true, 1);
insert into product(id, productName, description, unitPrice, active, version) values(2,'Product 2', 'Description 2', 10.00, true, 1);
insert into product(id, productName, description, unitPrice, active, version) values(3,'Product 3', 'Description 3', 15.00, true, 1);
insert into product(id, productName, description, unitPrice, active, version) values(4,'Product 4', 'Description 4', 20.00, true, 1);


insert into third_party(id, thirdParty, city) values(1,'Partener 1', 'Bucuresti');
insert into third_party(id, thirdParty, city) values(2,'Partener 2', 'Constanta');
insert into third_party(id, thirdParty, city) values(3,'Partener 3', 'Arad');
insert into third_party(id, thirdParty, city) values(4,'Partener 4', 'Cluj');

insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(1,1,'2023-04-26',10,0,1,1);
insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(2,1,'2023-04-27',5,1,1,1);
insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(3,1,'2023-04-28',10,0,1,1);

insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(4,2,'2023-04-15',10,0,1,1);
insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(5,2,'2023-04-16',10,0,1,1);

insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(6,3,'2023-04-09',10,0,1,1);
insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(7,3,'2023-04-10',5,1,1,1);

insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(8,4,'2023-04-01',10,1,1,1);
insert into stock_movement(id, product_id, operationDate, noOfUnits, operationType, third_party_id, version) values(9,4,'2023-04-02',10,1,1,1);