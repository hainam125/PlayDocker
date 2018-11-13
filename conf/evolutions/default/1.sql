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


# --- !Downs

drop table if exists product;

drop table if exists stock_item;

drop table if exists tag;

drop table if exists warehouse;

