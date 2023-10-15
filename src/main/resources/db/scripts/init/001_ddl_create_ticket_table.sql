create table if not exists ticket(
 number serial primary key,
 date timestamp,
 number_wait int
)