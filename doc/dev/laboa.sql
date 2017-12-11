/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/11 16:12:10                          */
/*==============================================================*/


/*==============================================================*/
/* Table: agenda                                                */
/*==============================================================*/
create table agenda
(
   agenda_id            int(10) unsigned not null auto_increment,
   owner_id             int(10) unsigned not null,
   title                varchar(255) not null,
   type                 varchar(255),
   next_time            datetime,
   quantity             int(10),
   unit                 varchar(255),
   remark               text,
   open                 tinyint(1),
   update_time          datetime,
   create_time          datetime,
   primary key (agenda_id)
);

/*==============================================================*/
/* Table: agenda_item                                           */
/*==============================================================*/
create table agenda_item
(
   item_id              int(10) unsigned not null auto_increment,
   agenda_id            int(10) unsigned not null,
   summary_time         datetime,
   primary key (item_id)
);

/*==============================================================*/
/* Table: agenda_summary                                        */
/*==============================================================*/
create table agenda_summary
(
   summary_id           int(10) not null auto_increment,
   item_id              int(10) unsigned not null,
   summarizer_id        int(10) unsigned not null,
   status               varchar(255),
   content              text,
   summary_time         datetime,
   primary key (summary_id)
);

/*==============================================================*/
/* Table: cooperation                                           */
/*==============================================================*/
create table cooperation
(
   cooperation_id       int(10) unsigned not null auto_increment,
   parent_id            int(10) unsigned,
   name                 varchar(255) not null,
   remark               text,
   begin_date           date,
   end_date             date,
   invite               tinyint(1),
   update_time          datetime,
   create_time          datetime,
   primary key (cooperation_id)
);

/*==============================================================*/
/* Table: cooperation_agenda                                    */
/*==============================================================*/
create table cooperation_agenda
(
   cooperation_agenda_id int(10) unsigned not null auto_increment,
   cooperation_id       int(10) unsigned not null,
   agenda_id            int(10) unsigned not null,
   primary key (cooperation_agenda_id)
);

/*==============================================================*/
/* Table: cooperation_agenda_participant                        */
/*==============================================================*/
create table cooperation_agenda_participant
(
   participant_id       int(10) unsigned not null auto_increment,
   member_id            int(10) unsigned not null,
   cooperation_agenda_id int(10) unsigned not null,
   primary key (participant_id)
);

/*==============================================================*/
/* Index: uk                                                    */
/*==============================================================*/
create unique index uk on cooperation_agenda_participant
(
   member_id,
   cooperation_agenda_id
);

/*==============================================================*/
/* Table: cooperation_apply                                     */
/*==============================================================*/
create table cooperation_apply
(
   apply_id             int(10) unsigned not null auto_increment,
   applicant_id         int(10) unsigned not null,
   cooperation_id       int(10) unsigned not null,
   status               varchar(255),
   update_time          datetime,
   apply_time           datetime,
   primary key (apply_id)
);

/*==============================================================*/
/* Table: cooperation_member                                    */
/*==============================================================*/
create table cooperation_member
(
   member_id            int(10) unsigned not null auto_increment,
   cooperation_id       int(10) unsigned not null,
   user_id              int(10) unsigned not null,
   role                 varchar(255),
   join_time            datetime,
   primary key (member_id)
);

/*==============================================================*/
/* Index: uk                                                    */
/*==============================================================*/
create unique index uk on cooperation_member
(
   cooperation_id,
   user_id
);

/*==============================================================*/
/* Table: file                                                  */
/*==============================================================*/
create table file
(
   file_id              int(11) unsigned not null auto_increment,
   user_id              int(10) unsigned not null,
   name                 varchar(255),
   address              varchar(255),
   remark               varchar(255),
   update_time          datetime,
   create_time          datetime,
   primary key (file_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int(10) unsigned not null auto_increment,
   username             varchar(255) not null,
   password             varchar(255) not null,
   salt                 varchar(255) not null,
   role                 varchar(255) not null,
   status               varchar(255) not null,
   login_time           datetime,
   register_time        datetime,
   primary key (user_id)
);

alter table user
   add unique AK_uk (username);

/*==============================================================*/
/* Table: userinfo                                              */
/*==============================================================*/
create table userinfo
(
   userinfo_id          int(10) unsigned not null auto_increment,
   user_id              int(10) unsigned not null,
   name                 varchar(255),
   employee_number      varchar(255),
   qq                   varchar(255),
   email                varchar(255),
   phone                varchar(255),
   primary key (userinfo_id)
);

alter table agenda add constraint FK_Reference_14 foreign key (owner_id)
      references user (user_id) on delete cascade on update cascade;

alter table agenda_item add constraint FK_Reference_11 foreign key (agenda_id)
      references agenda (agenda_id) on delete cascade on update cascade;

alter table agenda_summary add constraint FK_Reference_12 foreign key (item_id)
      references agenda_item (item_id) on delete cascade on update cascade;

alter table agenda_summary add constraint FK_Reference_13 foreign key (summarizer_id)
      references user (user_id) on delete cascade on update cascade;

alter table cooperation add constraint FK_Reference_17 foreign key (parent_id)
      references cooperation (cooperation_id) on delete cascade on update cascade;

alter table cooperation_agenda add constraint FK_Reference_15 foreign key (cooperation_id)
      references cooperation (cooperation_id) on delete cascade on update cascade;

alter table cooperation_agenda add constraint FK_Reference_16 foreign key (agenda_id)
      references agenda (agenda_id) on delete cascade on update cascade;

alter table cooperation_agenda_participant add constraint FK_Reference_18 foreign key (member_id)
      references cooperation_member (member_id) on delete cascade on update cascade;

alter table cooperation_agenda_participant add constraint FK_Reference_19 foreign key (cooperation_agenda_id)
      references cooperation_agenda (cooperation_agenda_id) on delete cascade on update cascade;

alter table cooperation_apply add constraint FK_Reference_10 foreign key (cooperation_id)
      references cooperation (cooperation_id) on delete cascade on update cascade;

alter table cooperation_apply add constraint FK_Reference_9 foreign key (applicant_id)
      references user (user_id) on delete cascade on update cascade;

alter table cooperation_member add constraint FK_Reference_7 foreign key (cooperation_id)
      references cooperation (cooperation_id) on delete cascade on update cascade;

alter table cooperation_member add constraint FK_Reference_8 foreign key (user_id)
      references user (user_id) on delete cascade on update cascade;

alter table file add constraint FK_Reference_2 foreign key (user_id)
      references user (user_id) on delete cascade on update cascade;

alter table userinfo add constraint FK_Reference_1 foreign key (user_id)
      references user (user_id) on delete cascade on update cascade;

