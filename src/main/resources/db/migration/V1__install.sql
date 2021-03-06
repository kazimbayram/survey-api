drop table if exists answers CASCADE ;
drop table if exists sequence_table CASCADE ;
drop table if exists topic_scoreboard CASCADE ;
drop table if exists topics CASCADE ;
create table answers (submit_id integer not null, inserted_date timestamp, updated_date timestamp, version integer, feedback varchar(255), score integer, topic_id integer not null, primary key (submit_id));
create table sequence_table (table_name varchar(255) not null, next bigint, primary key (table_name));
insert into sequence_table(table_name, next) values ('answers',0);
insert into sequence_table(table_name, next) values ('topics',0);
insert into sequence_table(table_name, next) values ('topic_scoreboard',0);
create table topic_scoreboard (scoreboard_id integer not null, inserted_date timestamp, updated_date timestamp, version integer, total_detractors integer not null, total_passives integer not null, total_promoters integer not null, total_submission integer not null, topic_id integer not null, primary key (scoreboard_id));
create table topics (topic_id integer not null, inserted_date timestamp, updated_date timestamp, version integer, question varchar(255) not null, score integer not null, topic varchar(255) not null, primary key (topic_id));
alter table answers add constraint FK4drapsx8v4hd26tppt6m6m5hp foreign key (topic_id) references topics;
alter table topic_scoreboard add constraint FK96w5xl306h18o0fb2pik2cptm foreign key (topic_id) references topics;
