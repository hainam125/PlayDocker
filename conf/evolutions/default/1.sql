# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  ean                           varchar(255),
  name                          varchar(255),
  description                   varchar(255),
  picture                       varbinary(255)
);

create table stock_item (
  name                          varchar(255)
);

create table tag (
  id                            bigint,
  name                          varchar(255)
);

create table warehouse (
  quantity                      bigint
);


# --- !Downs

drop table if exists product;

drop table if exists stock_item;

drop table if exists tag;

drop table if exists warehouse;

