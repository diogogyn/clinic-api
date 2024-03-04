create table usuarios_perfis(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    perfis_id bigint not null,
    primary key(id)
);