# --- Initialize tags

# --- !Ups
insert into tag(id,name) values (1, 'lightweight');
insert into tag(id,name) values (2, 'metal');
insert into tag(id,name) values (3, 'plastic');

# --- !Downs
SET REFERENTIAL_INTEGRITY FALSE;
delete from tag;