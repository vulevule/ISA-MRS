#users 
insert into configurationexample.user (type, email, address, name, password, phone, role, surname, first_login) values
("System_admin", "nadjazorboski@gmail.com", "Novosadskog sajma 5, Novi Sad", "Nadja" , "nadjazorboski", "5655", 1, "Zorboski", 0); #sistem admin
insert into configurationexample.user (type, email, address, name, password, phone, role, surname, activate) values
("Visitor", "jocaftn15@gmail.com", "Novosadskog sajma 5, Novi Sad", "Jovana" , "jovanajovanovic", "5655", 0, "Jovanovic", 1); #posetilac
insert into configurationexample.user (type, email, address, name, password, phone, role, surname, activate) values
("Visitor", "jovanaftn@yahoo.com", "Novosadskog sajma 5, Novi Sad", "Jovana" , "jovanajovanovic", "5655", 0, "Jovanovic", 1); #posetilac

insert into configurationexample.user (type, email, address, name, password, phone, role, surname, first_login) values
("Theater_place_admin", "jovana@gmail.com", "Novosadskog sajma 5, Novi Sad", "Jovana" , "jovanajovanovic", "5655", 3, "Jovanovic", 0); #admin place

#friendship 

insert into configurationexample.friendship (id, receiver, sender, status) VALUES (1, "jocaftn15@gmail.com", "jovanaj33@gmail.com", 0);#prijatelji
insert into configurationexample.friendship (id, receiver, sender, status) VALUES (2, "jovanaftn@yahoo.com", "jovanaj33@gmail.com", 2);#poslat zahtev

#place
insert into configurationexample.place (id, address, description,name, type, user_email)
values(1, "Trg Slobode", "lep bioskop", "Bioskop", 0, "jovana@gmail.com");

#projection
insert into configurationexample.projection (id, average_rating, banner, cast, description, director, duration, genre, name, num_of_visitors, type, place_id) values
(1, 2,"b1", "cast", "description", "director",120, "genre", "name", 1, 0,1);

#arena
insert into configurationexample.arena (id, column_seats, name, row_seats, place_id)
values(1, 10, "Arena 1", 6, 1);

#term
insert into configurationexample.term (id, price, projection_date, projection_time, arena_id, projection_id)
values (1, 200, '2018-7-04', "15:50:00",1,1);

