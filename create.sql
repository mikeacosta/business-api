create table categories (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table clients (id bigint not null auto_increment, firstname varchar(255), lastname varchar(255), primary key (id)) engine=InnoDB;
create table providers (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table work_categories (work_id bigint not null, category_id bigint not null, primary key (work_id, category_id)) engine=InnoDB;
create table works (id bigint not null auto_increment, description varchar(255), name varchar(255), provider_id bigint not null, primary key (id)) engine=InnoDB;
alter table work_categories add constraint FKkb4sk812657m6yglotky9xkhr foreign key (category_id) references categories (id);
alter table work_categories add constraint FK3s4qo7redh1fvyb8usj575gtc foreign key (work_id) references works (id);
alter table works add constraint FK3g7u8idrkt6de2dcq9wqh7lhg foreign key (provider_id) references providers (id);
