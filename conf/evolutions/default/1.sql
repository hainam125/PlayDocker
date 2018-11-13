# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                            bigint auto_increment not null,
  ean                           varchar(255),
  name                          varchar(255),
  description                   varchar(255),
  picture                       varbinary(255),
  constraint pk_product primary key (id)
);

create table stock_item (
  id                            bigint auto_increment not null,
  warehouse_id                  bigint,
  product_id                    bigint,
  quantity                      bigint,
  constraint pk_stock_item primary key (id)
);

create table tag (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_tag primary key (id)
);

create table warehouse (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_warehouse primary key (id)
);

alter table stock_item add constraint fk_stock_item_warehouse_id foreign key (warehouse_id) references warehouse (id) on delete restrict on update restrict;
create index ix_stock_item_warehouse_id on stock_item (warehouse_id);

alter table stock_item add constraint fk_stock_item_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_stock_item_product_id on stock_item (product_id);


# --- !Downs

alter table stock_item drop constraint if exists fk_stock_item_warehouse_id;
drop index if exists ix_stock_item_warehouse_id;

alter table stock_item drop constraint if exists fk_stock_item_product_id;
drop index if exists ix_stock_item_product_id;

drop table if exists product;

drop table if exists stock_item;

drop table if exists tag;

drop table if exists warehouse;

