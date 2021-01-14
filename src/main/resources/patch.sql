drop table if exists circuit_paper;
drop table if exists circuit_tag;
drop table if exists circuit;
drop table if exists user;
drop table if exists basis;
drop table if exists permission;
drop table if exists paper_author;
drop table if exists paper_tag;
drop table if exists paper;
drop table if exists tag;
drop table if exists author;

create table if not exists permission(
    id            int auto_increment primary key,
    name          varchar(255) not null
);

create table if not exists user(
    id            int auto_increment primary key,
    login         varchar(255) not null,
    password      varchar(255) not null,
    permission_id int          not null,
    FOREIGN KEY (permission_id)  REFERENCES permission (id)
);

create table if not exists basis(
                                    id            int auto_increment primary key,
                                    name          varchar(255) not null,
                                    description   text         not null
);

create table if not exists circuit (
    id             int auto_increment primary key,
    name           varchar(255) not null,
    description    text         not null,
    ckt            text         not null,
    basis_id       int          not null,
    truth_table    text         not null,
    user_id        int          not null,
    checked        bit          not null,
    FOREIGN KEY (user_id)  REFERENCES user (id),
    FOREIGN KEY (basis_id)  REFERENCES basis (id)
);

create table if not exists paper(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    description text         not null,
    place       text         not null,
    doi         varchar(255) not null
);

create table if not exists circuit_paper(
    id            int auto_increment primary key,
    circuit_id    int        not null,
    paper_id      int        not null,
    FOREIGN KEY (circuit_id)  REFERENCES circuit (id),
    FOREIGN KEY (paper_id)  REFERENCES paper (id)
);

create table if not exists tag(
    id            int auto_increment primary key,
    name          varchar(255) not null
);

create table if not exists circuit_tag(
    id            int auto_increment primary key,
    circuit_id    int        not null,
    tag_id        int        not null,
    FOREIGN KEY (circuit_id)  REFERENCES circuit (id),
    FOREIGN KEY (tag_id)  REFERENCES tag (id)
);

create table if not exists paper_tag(
    id            int auto_increment primary key,
    paper_id      int        not null,
    tag_id        int        not null,
    FOREIGN KEY (paper_id)  REFERENCES paper (id),
    FOREIGN KEY (tag_id)  REFERENCES tag (id)
);

create table if not exists author(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    description text         not null
);

create table if not exists paper_author(
    id            int auto_increment primary key,
    paper_id      int        not null,
    author_id     int        not null,
    FOREIGN KEY (paper_id)  REFERENCES paper (id),
    FOREIGN KEY (author_id)  REFERENCES author (id)
);

insert into permission (name) values ('admin');
insert into permission (name) values ('observer');
insert into permission (name) values ('author');

insert into tag (name) values ('sum');
insert into tag (name) values ('mod');
insert into tag (name) values ('ex');
insert into tag (name) values ('th');

insert into basis (name, description) values ('U2', 'description');
insert into basis (name, description) values ('B2', 'description');

insert into user (login, password, permission_id) values ('admin', 'admin', 1);
insert into user (login, password, permission_id) values ('guest', '123', 2);
insert into user (login, password, permission_id) values ('nikita', '123', 3);

insert into author (name, description) values ('Alexander S. Kulikov', 'alexanderskulikov.github.io');
insert into author (name, description) values ('Nigmatullin', 'description');
insert into author (name, description) values ('Knuth D', 'description');
insert into author (name, description) values ('Kojevnikov A', 'description');

insert into paper (name, description, place, doi)
values ('Models of lower-bounds proofs','desc','Theoretical Computer Science, vol. 52 (1987)','doi:01010101');
insert into paper (name, description, place, doi)
values ('Are lower bounds on the complexity lower bounds for universal circuits?','desc','place','doi:34723827632');
insert into paper (name, description, place, doi)
values ('Circuit Complexity and Multiplicative Complexity of Boolean Functions','desc','Proceedings of Computability in Europe, vol. 6158 (2010)','doi:4382811838');
insert into paper (name, description, place, doi) values ('Satisfiability','desc','place','doi:4443383833');

insert into paper_author (paper_id, author_id) values (1, 2);
insert into paper_author (paper_id, author_id) values (2, 2);
insert into paper_author (paper_id, author_id) values (3, 1);
insert into paper_author (paper_id, author_id) values (3, 4);
insert into paper_author (paper_id, author_id) values (4, 3);

insert into paper_tag (paper_id, tag_id) values (1, 1);
insert into paper_tag (paper_id, tag_id) values (1, 2);
insert into paper_tag (paper_id, tag_id) values (2, 2);
insert into paper_tag (paper_id, tag_id) values (4, 1);
insert into paper_tag (paper_id, tag_id) values (4, 3);

insert into circuit (name, description, ckt, basis_id, truth_table, user_id, checked)
values ('sum3','Circuit of sum3 function','ckt of sum3 here', 1, 'tt of sum3 here', 3, True);
insert into circuit (name, description, ckt, basis_id, truth_table, user_id, checked)
values ('sum7','Circuit of sum7 function','ckt of sum7 here', 1, 'tt of sum7 here', 3, False);
insert into circuit (name, description, ckt, basis_id, truth_table, user_id, checked)
values ('mod3','Circuit of mod3 function','ckt of mod3 here', 1, 'tt of mod3 here', 3, True);
insert into circuit (name, description, ckt, basis_id, truth_table, user_id, checked)
values ('th2','Circuit of th2 function','ckt of th2 here', 2, 'tt of th2 here', 1, False);

insert into circuit_paper (circuit_id, paper_id) values (1, 1);
insert into circuit_paper (circuit_id, paper_id) values (1, 2);
insert into circuit_paper (circuit_id, paper_id) values (1, 4);
insert into circuit_paper (circuit_id, paper_id) values (2, 3);
insert into circuit_paper (circuit_id, paper_id) values (2, 4);
insert into circuit_paper (circuit_id, paper_id) values (3, 2);
insert into circuit_paper (circuit_id, paper_id) values (4, 2);

insert into circuit_tag (circuit_id, tag_id) values (1, 1);
insert into circuit_tag (circuit_id, tag_id) values (2, 1);
insert into circuit_tag (circuit_id, tag_id) values (3, 2);
insert into circuit_tag (circuit_id, tag_id) values (4, 4);

