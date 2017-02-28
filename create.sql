create table Admin(
	AdminId int not null auto_increment,
	AdminName char(12) not null ,
	AdminPassword char(16) not null,
	primary key(AdminId)
);
create table Config(
	ConfigId int not null auto_increment,
	ConfigKey char(16) not null ,
	ConfigValue char(32) not null,
	primary key(ConfigId)
);