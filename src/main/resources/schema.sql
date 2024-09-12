drop table if exists address cascade
drop table if exists brand cascade
drop table if exists member cascade
drop table if exists product cascade

create table address (id bigint generated by default as identity, member_id BINARY(16), city varchar(50) not null, district varchar(50) not null, province varchar(50) not null, created_at timestamp(6) not null, updated_at timestamp(6) not null, primary key (id))
create table brand (id bigint generated by default as identity, member_id BINARY(16), name varchar(100) not null, license_number bigint not null, phone_number bigint not null, created_at timestamp(6) not null, updated_at timestamp(6) not null, primary key (id))
create table member (member_id BINARY(16) not null, email varchar(50) not null, nickname varchar(50) not null, password varchar(100) not null, role tinyint check (role between 0 and 1) not null, status boolean not null, created_at timestamp(6) not null, updated_at timestamp(6) not null, primary key (member_id))
create table product (id bigint generated by default as identity, brand_id bigint, category tinyint not null check (category between 0 and 7), name varchar(100) not null, price numeric(38,2) not null, quantity integer not null, created_at timestamp(6) not null, updated_at timestamp(6) not null, primary key (id))


alter table if exists address add constraint fk_member_address foreign key (member_id) references member
alter table if exists brand add constraint fk_member_brand foreign key (member_id) references member
alter table if exists product add constraint fk_brand_product foreign key (brand_id) references brand