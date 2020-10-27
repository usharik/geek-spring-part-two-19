alter table `products` drop FOREIGN KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv`;
GO

alter table `products` drop FOREIGN KEY `FKog2rp4qthbtt2lfyhfo32lsw9`;
GO

drop table `products`;
GO

alter table `brands` drop key `UC_BRANDSNAME_COL`;
GO

drop table  `brands`;
GO

alter table `categories` drop key `UC_CATEGORIESNAME_COL`;
GO

drop table  `categories`;
GO