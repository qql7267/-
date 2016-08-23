select * from shop_user;
delete from shop_user;
insert into shop_user values(0,'admin','000',999999.0,0,3,0);
insert into shop_user values(1,111,111,955.0,9,1,1);
insert into shop_user values(2,222,222,1045.0,8,2,1);
insert into shop_user values(3,333,333,1000.0,7,1,1);
insert into shop_user values(4,444,444,1000.0,6,1,1);
insert into shop_user values(5,555,555,1000.0,5,1,1);
insert into shop_user values(6,666,666,1000.0,10,1,1);
insert into shop_user values(7,777,777,1000.0,10,1,1);
insert into shop_user values(8,888,888,1000.0,10,1,1);
insert into shop_user values(9,999,999,1000.0,10,2,1);
insert into shop_user values(10,'aaa','aaa',1000.0,10,2,1);
insert into shop_user values(11,'sss','sss',1000.0,10,2,1);
insert into shop_user values(12,'ddd','ddd',1000.0,10,2,1);

select * from shop_goods;
delete from shop_goods;

insert into shop_goods values(1,'卫尤',1000,100.0,'辣条',2);
insert into shop_goods values(2,'老于妈',100,10.0,'零食',2);
insert into shop_goods values(3,'姜',900,0.5,'辣条',2);
insert into shop_goods values(4,'浪昧仙',100,10.0,'零食',9);
insert into shop_goods values(5,'冶宝',100,10.0,'饮料',9);
insert into shop_goods values(6,'恰恰瓜子',100,20.0,'零食',10);
insert into shop_goods values(7,'坑师傅',100,50.0,'饮料',11);
insert into shop_goods values(8,'三鲜姨面',100,100.0,'辣条',12);
insert into shop_goods values(9,'雷碧',100,3.0,'饮料',12);

select * from shop_shopcart;
delete from shop_shopcart;
insert into shop_shopcart values(1,1,3,10);
insert into shop_shopcart values(2,1,9,3);
insert into shop_shopcart values(3,3,3,100);
insert into shop_shopcart values(4,3,6,1);





























