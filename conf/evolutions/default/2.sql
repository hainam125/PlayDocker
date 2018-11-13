# --- Initialize tags

# --- !Ups
insert into tag(id,name) values (1, 'lightweight');
insert into tag(id,name) values (2, 'metal');
insert into tag(id,name) values (3, 'plastic');

insert into product(id, ean, name, description) values ( 1, '0000000000001', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 2, '0000000000002', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 3, '0000000000003', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 4, '0000000000004', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 5, '0000000000005', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 6, '0000000000006', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 7, '0000000000007', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 8, '0000000000008', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values ( 9, '0000000000009', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values (10, '0000000000010', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values (11, '0000000000011', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values (12, '0000000000012', 'Paperclip 0', 'Paperclip');
insert into product(id, ean, name, description) values (13, '0000000000013', 'Paperclip 0', 'Paperclip');

# --- !Downs
SET REFERENTIAL_INTEGRITY FALSE;
delete from tag;
delete from product;