-- Account
INSERT INTO account (username, password, role) VALUES
('user1', '123', 'user'),
('user2', '123', 'user'),
('user3', '123', 'user'),
('user4', '123', 'user'),
('user5', '123', 'user'),
('user6', '123', 'user'),
('user7', '123', 'user'),
('user', '123', 'user'),
('driver', '123', 'driver'),
('admin', '123', 'admin');

-- Customer
INSERT INTO customer (customerID, name, sex, phone_number, email, address, img, username) VALUES
('U-12345678', N'Nguyễn Văn Tiến', 0, '0123456789', 'customer1@example.com', N'123 Đường 2/9, Quận Hải Châu, TP. Đà Nẵng', '', 'user1'),
('U-23456789', N'Trần Thị Hương', 0, '0123456789', 'customer2@example.com', N'456 Đường Hàm Nghi, Quận Thanh Khê, TP. Đà Nẵng', '', 'user2'),
('U-34567891', N'Lê Văn Quang', 1, '0123456789', 'customer3@example.com', N'789 Đường Lê Lợi, Quận Liên Chiểu, TP. Đà Nẵng', '', 'user3'),
('U-45678912', N'Phạm Thị Anh', 0, '0123456789', 'customer4@example.com', N'012 Đường Ngô Quyền, Quận Sơn Trà, TP. Đà Nẵng', '', 'user4'),
('U-56789123', N'Trần Văn Đông', 1, '0123456789', 'customer5@example.com', N'345 Đường Trưng Nữ Vương, Quận Ngũ Hành Sơn, TP. Đà Nẵng', '', 'user5'),
('U-67891234', N'Lê Thị Bích', 0, '0123456789', 'customer6@example.com', N'678 Đường Trần Phú, Quận Cẩm Lệ, TP. Đà Nẵng', '', 'user6'),
('U-78912345', N'Nguyễn Văn Hải', 1, '0123456789', 'customer7@example.com', N'901 Đường Lý Thường Kiệt, Quận Hòa Vang, TP. Đà Nẵng', '', 'user7'),
('U-123', N'Nguyễn Minh Thành', 1, '0123456789', 'test@example.com', N'901 Đường Hùng Vương, Quận Hòa Vang, TP. Đà Nẵng', '', 'user')

INSERT INTO [dbo].[restaurant]
           ([restaurantid]
           ,[address]
           ,[email]
           ,[img]
           ,[phone_number]
           ,[username])
     VALUES
           ('R01', N'190 Thanh Hoá', 'tfive@gmail.com', '', '098745612', 'admin');



-- Category
INSERT INTO category (categoryID, name, restaurantid) VALUES
('C-12345678', N'Đồ Ăn Nhanh', 'R01'),
('C-23456789', N'Trà Sữa', 'R01'),
('C-34567891', N'Cà Phê', 'R01'),
('C-45678912', N'Kem', 'R01'),
('C-56789123', N'Bánh Ngọt', 'R01'),
('C-67891234', N'Pizza', 'R01'),
('C-89123456', N'Bánh Mì', 'R01'),
('C-12345670', N'Nước Ép', 'R01'),
('C-34567812', N'Chè', 'R01')

-- Dish
INSERT INTO dish (dishID, name, description, img, price, discount_price, status, restaurantid, categoryid) VALUES
('P-23456789', N'Trà Sữa Trân Châu Đường Đen', N'Trà sữa ngon, thêm trân châu đường đen', null, 35000, 0, 1, 'R01', 'C-23456789'),
('P-34567891', N'Cà Phê Đen', N'Cà phê đen thơm ngon', null, 30000, 0, 1, 'R01', 'C-34567891'),
('P-45678912', N'Kem Chocolate', N'Kem chocolate mát lạnh', null, 40000, 0, 1, 'R01', 'C-45678912'),
('P-56789123', N'Bánh Flan Caramel', N'Bánh flan thơm ngon', null, 45000, 0, 1, 'R01', 'C-56789123'),
('P-67891234', N'Pizza Hải Sản', N'Pizza hải sản thơm ngon', null, 55000, 0, 1, 'R01', 'C-67891234')

-- Driver
INSERT INTO driver (driverID, license_plates, name, sex, phone_number, email, motorbike, work_status, identification_card, img, username) VALUES
('D-12345678', '29A-12345', N'Nguyễn Văn Tài', 1, '0123456789', 'tainguyen@gmail.com', 'Wave Alpha', 1, '123456789012', '', 'driver')

-- DriverRegister
INSERT INTO driver_register_form (phone_number, name, email, identification_card) VALUES
('0123456789', N'Nguyễn Văn A', 'nguyenvana@gmail.com', '123456789012'),
('0987654323', N'Trần Thị B', 'tranthib@gmail.com', '234567890123'),
('0123452389', N'Lê Văn C', 'levanc@gmail.com', '345678901234'),
('0987234321', N'Phạm Thị D', 'phamthid@gmail.com', '456789012345'),
('0125456789', N'Hoàng Văn E', 'hoangve@gmail.com', '567890123456'),
('0345654321', N'Mai Thị F', 'maithif@gmail.com', '678901234567'),
('0156456789', N'Trần Văn G', 'tranvang@gmail.com', '789012345678'),
('0987344321', N'Nguyễn Thị H', 'nguyenthih@gmail.com', '890123456789'),
('0127656789', N'Lê Văn I', 'levani@gmail.com', '901234567890'),
('0982894321', N'Phạm Thị K', 'phamthik@gmail.com', '012345678901'),
('0127666789', N'Trần Văn L', 'tranvanl@gmail.com', '123456789012'),
('0982343221', N'Hồ Thị M', 'hothim@gmail.com', '234567890123'),
('0126746789', N'Nguyễn Văn N', 'nguyenvann@gmail.com', '345678901234'),
('0982344321', N'Lê Thị O', 'lethio@gmail.com', '456789012345'),
('0123223789', N'Phạm Văn P', 'phamvanp@gmail.com', '567890123456'),
('0987654321', N'Mai Thị Q', 'maithiq@gmail.com', '678901234567'),
('0687654321', N'Nguyễn Thị S', 'nguyenthis@gmail.com', '890123456789')

-- Order
INSERT INTO orders (orderID, status, total_price, total_quantity, note_for_restaurant, note_for_driver, ship_money, restaurantid, customerid, driverid, voucherid) VALUES
('O-12345678', N'Xác nhận', 150000, 3, N'Chua cay vua', N'Giao cẩn thận', 20000, 'R01', 'U-123', 'D-12345678', NULL),
('O-23456789', N'Hoàn thành', 200000, 4, N'Giao nhanh nhất', N'Gọi điện khi gần', 25000, 'R01', 'U-123', 'D-12345678', NULL),
('O-34567891', N'Hoàn thành', 180000, 3, N'Ít đường', N'Giao cẩn thận', 22000, 'R01', 'U-123', 'D-12345678', NULL),
('O-45678912', N'Đã đặt', 220000, 5, N'Không ớt', N'Gọi trước khi giao', 20000, 'R01', 'U-56789123', null, NULL),
('O-56789123', N'Hoàn thành', 250000, 6, N'Nhiều đá', N'Giao cẩn thận', 25000, 'R01', 'U-123', 'D-12345678', NULL),
('O-67891234', N'Đã lấy', 300000, 8, N'Không đường', N'Gọi trước khi giao', 30000, 'R01', 'U-123', 'D-12345678', NULL),
('O-78912345', N'Xác nhận', 270000, 7, N'Chua cay nhẹ', N'Giao cẩn thận', 28000, 'R01', 'U-56789123', null, NULL),
('O-89123456', N'Đã nhận', 200000, 4, N'Nhiều nước sốt', N'Giao cẩn thận', 22000, 'R01', 'U-56789123', 'D-12345678', NULL),
('O-91234567', N'Xác nhận', 180000, 3, N'Không đường', N'Gọi trước khi giao', 20000, 'R01', 'U-56789123', null, NULL),
('O-12345670', N'Hoàn thành', 240000, 5, N'Chua ngọt vừa', N'Giao cẩn thận', 25000, 'R01', 'U-123', 'D-12345678', NULL),
('O-23456781', N'Đang giao', 280000, 6, N'Nhiều phô mai', N'Gọi điện khi gần', 30000, 'R01', 'U-56789123', 'D-12345678', NULL),
('O-34567892', N'Đã đặt', 190000, 3, N'Không ớt', N'Giao cẩn thận', 22000, 'R01', 'U-56789123', null, NULL),
('O-45678903', N'Đã đặt', 200000, 4, N'Chua cay vừa', N'Gọi trước khi giao', 20000, 'R01', 'U-56789123', null, NULL),
('O-56789014', N'Đã đặt', 260000, 7, N'Nhiều sốt ớt', N'Gọi điện khi gần', 28000, 'R01', 'U-56789123', null, NULL),
('O-67890125', N'Đã đặt', 230000, 6, N'Không đường', N'Giao cẩn thận', 24000, 'R01', 'U-123', null, NULL),
('O-89112347', N'Đã nhận', 210000, 5, N'Nhiều ớt', N'Gọi điện khi gần', 22000, 'R01', 'U-123', 'D-12345678', NULL)

-- OrderDetail
INSERT INTO order_detail (quantity, total_amount, dishid, orderid) VALUES
(2, 60000, 'P-34567891', 'O-12345678'),
(2, 60000, 'P-56789123', 'O-12345678'),
(2, 60000, 'P-23456789', 'O-12345678'),
(3, 90000, 'P-56789123', 'O-23456789')


-- Voucher
INSERT INTO voucher (voucherID, description, reduced_price, minimum_price, is_share, voucher_type, restaurantid) VALUES
('V-12345678', N'Giảm 10% cho đơn hàng trên 200,000 VND',  45000, 200000, 1, 1, 'R01'),
('V-23456789', N'Giảm 20% cho đơn hàng trên 500,000 VND',  5000, 500000, 1, 0, 'R01'),
('V-34567890', N'Giảm 30% cho đơn hàng trên 1,000,000 VND',  210000, 1000000, 0, 1, 'R01'),
('V-45678901', N'Giảm 15% cho đơn hàng trên 300,000 VND',  38000, 300000, 1, 0, 'R01'),
('V-56789012', N'Giảm 25% cho đơn hàng trên 800,000 VND', 10000, 800000, 0, 1, 'R01'),
('V-67890123', N'Giảm 12% cho đơn hàng trên 250,000 VND', 60000, 250000, 1, 0, 'R01'),
('V-78901234', N'Giảm 18% cho đơn hàng trên 400,000 VND',12000, 400000, 0, 1, 'R01'),
('V-89123456', N'Giảm 8% cho đơn hàng trên 150,000 VND', 3000, 150000, 1, 0, 'R01'),
('V-91234567', N'Giảm 22% cho đơn hàng trên 600,000 VND', 7000, 600000, 0, 1, 'R01'),
('V-12345670', N'Giảm 28% cho đơn hàng trên 700,000 VND', 12000, 700000, 1, 0, 'R01')

