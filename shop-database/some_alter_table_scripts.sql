alter table `products_pictures` drop FOREIGN KEY `FKh3amnci4cl7xcl1al140xw79e`;

-- сначала нужно удалить внешний ключ
alter table `pictures` drop FOREIGN KEY `FKe9cv52k04xoy6cj8xy308gnw3`;

alter table `pictures` drop key `UK_ehsu2tyinopypjox1ijxt3g3c`;

alter table `products_pictures` add CONSTRAINT `FKh3amnci4cl7xcl1al140xw79e`
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

alter table `pictures` add CONSTRAINT `FKe9cv52k04xoy6cj8xy308gnw3`
    FOREIGN KEY (`picture_data_id`) REFERENCES `pictures_data` (`id`);

alter table `pictures` add CONSTRAINT
    UNIQUE KEY `UK_ehsu2tyinopypjox1ijxt3g3c` (`picture_data_id`);