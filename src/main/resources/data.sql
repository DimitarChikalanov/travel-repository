CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Dimitar', 'Dimtrov', 'admin');
insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Dimitar', 'Dimtrov', 'dimitar55');
insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Pesho', 'Dimtrov', 'pesho33');
