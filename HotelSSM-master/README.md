# HotelSSM

|接口|URI|HTTP方法|
|---|---|---|
|根据用户id查询该用户的所有订单|localhost:8080/HotelSSM/order/user/{user_id}|GET|
|删除指定id的订单|localhost:8080/HotelSSM/order/{id}|DELETE|
|指定id的订单状态改为已付款|localhost:8080/HotelSSM/order/pay/{id}|POST|
|新增用户订单|localhost:8080/HotelSSM/order?user_id=&room_id=&book_num=&guest=&phone=|PUT|
|查询所有房间的状态|localhost:8080/HotelSSM/room|GET|
|增加指定类型房间剩余数量|localhost:8080/HotelSSM/room/addNum?book_num=&room_type=|POST|
|减少指定类型房间剩余数量|localhost:8080/HotelSSM/room/reduceNum?book_num=&room_type=|POST|
|计算两种类型房间的差价|localhost:8080/HotelSSM/room/calPrice?room_type=&old_room_type=|GET|