--liquibase formatted sql
--changeset ctran79:20210710-1400-init-db.sql

create table dictionaries
(
    id          serial primary key,
    code        varchar(255) unique,
    name        varchar(255),

    create_date timestamp default current_timestamp,
    update_date timestamp default current_timestamp
);

create table dictionary_values
(
    id            serial primary key,
    dictionary_id int8 not null,
    code          varchar(255),
    value         varchar(255),

    create_date   timestamp default current_timestamp,
    update_date   timestamp default current_timestamp,

    constraint value_unique unique (dictionary_id, value)
);

create table drugs
(
    id          serial primary key,
    name        varchar(1023),
    usage       varchar(1023),

    create_date timestamp default current_timestamp,
    update_date timestamp default current_timestamp
);

create table patients
(
    id          serial primary key,
    name        varchar(1023),
    birthday    timestamp,
    sex_id      int8,
    telephone   varchar(255),
    address     varchar(255),
    weight      int,
    height      int,
    is_examined bit,

    create_date timestamp default current_timestamp,
    update_date timestamp default current_timestamp,

    constraint FK_patient_sex foreign key (sex_id) references dictionary_values (id)
);

create table prescriptions
(
    id          serial primary key,
    patient_id  int8 not null,

    create_date timestamp default current_timestamp,
    update_date timestamp default current_timestamp,

    constraint FK_prescription_patient foreign key (patient_id) references patients (id)
);

create table diagnoses
(
    id              serial primary key,
    prescription_id int8 not null,
    seq_no          int  not null,
    diagnosis_id    int8 not null,

    create_date     timestamp default current_timestamp,
    update_date     timestamp default current_timestamp,

    constraint FK_diagnosis_prescription foreign key (prescription_id) references prescriptions (id),
    constraint FK_diagnosis_indication foreign key (diagnosis_id) references dictionary_values (id)
);

create table indications
(
    id              serial primary key,
    prescription_id int8 not null,
    seq_no          int  not null,
    drug_id         int  not null,
    quantity        double precision,
    unit_id         int  not null,
    usage           varchar(1023),

    create_date     timestamp default current_timestamp,
    update_date     timestamp default current_timestamp,

    constraint FK_indication_prescription foreign key (prescription_id) references prescriptions (id),
    constraint FK_indication_drug foreign key (drug_id) references drugs (id),
    constraint FK_indication_unit foreign key (unit_id) references dictionary_values (id)
);

insert into dictionaries(code, name)
values ('ICD10', 'Tên bệnh');

insert into dictionaries(code, name)
values ('DRUG_UNIT', 'Đơn vị thuốc');

insert into dictionaries(code, name)
values ('GENDER', 'Giới tính');

insert into dictionary_values(dictionary_id, code, value)
select id, 'VIEN', 'Viên'
from dictionaries
where code = 'DRUG_UNIT';

insert into dictionary_values(dictionary_id, code, value)
select id, 'CHAI', 'Chai'
from dictionaries
where code = 'DRUG_UNIT';

insert into dictionary_values(dictionary_id, code, value)
select id, 'TUYP', 'Tuýp'
from dictionaries
where code = 'DRUG_UNIT';

insert into dictionary_values(dictionary_id, code, value)
select id, 'NAM', 'Nam'
from dictionaries
where code = 'GENDER';

insert into dictionary_values(dictionary_id, code, value)
select id, 'NU', 'Nữ'
from dictionaries
where code = 'GENDER';

insert into dictionary_values(dictionary_id, code, value)
select id, 'KHAC', 'Khác'
from dictionaries
where code = 'GENDER';