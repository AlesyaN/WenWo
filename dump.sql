create table "user"(
  id SERIAL PRIMARY KEY,
  login text UNIQUE NOT NULL,
  photo_path text,
  password text not null,
  name text,
  surname text,
  email text UNIQUE not null,
  city text,
  gender text,
  dateOfBirth date
);

create table message (
    id serial primary key,
    sender_id int references "user"(id),
    reciever_id int references "user"(id),
    message text,
    "date" timestamp
  );

create table question (
    id serial primary key,
    sender_id int references "user"(id),
    reciever_id int references "user"(id),
    message text,
    "date" timestamp,
    answered boolean
);

create table "like" (
  id serial primary key,
  user_id int references "user"(id),
  question_id int references question(id)
);

create table subscriptions (
  id serial primary key,
  subscriptor_id int references "user"(id),
  subscriber_id int references "user"(id)
);

insert into "user"(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('kama', 'qwerty', 'Kamila', 'Nigmetzyanova', 'kama@mail.ru', 'NCh', 'female', '09/04/1999');
insert into "user"(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('lesya', 'qwerty', 'Olesya', 'Nasibullina', 'lesya@mail.ru', 'Kazan', 'female', '06/05/1999');
insert into "user"(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('tim', 'qwerty', 'Timur', 'Badretdinov', 'tim@mail.ru', 'NCh', 'male', '02/04/1999');
insert into "user"(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('aina', 'qwerty', 'Aina', 'Ardashirova', 'aina@mail.ru', 'Ufa', 'female', '14/02/1999');

insert into message (sender_id, reciever_id, message, "date")
  values (1, 5, 'hello!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (5, 3, 'how are you!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (1, 4, 'Beijing is cool!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (4, 3, 'I like China!', now());
