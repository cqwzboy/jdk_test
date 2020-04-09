-- sharding_rep
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10000', '135235363472468368', '8839ed00b92344908fbe40af4be0dbeb', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10001', '1352353634', '86e77ef645814c71bcfd198db105eeda', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10002', '1352353634', '51886b51284e436f91a4d1406336c042', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10005', '1352353634', 'c2f769da61554c66a28eda52621ea2e4', '12515151', '????-2', '325522');
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10006', '1352353634', 'e018be6ba3da40f88b31ffbd8f123673', '12515151', '????-2', '325522');
INSERT INTO `sharding_rep`.`test_order` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10007', '1352353635', '78bf9037a6b742c2b30979730e910a44', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep`.`worker` (`id`, `name`, `money`) VALUES ('3', 'zhangsan', '10000313');

-- sharding_rep_0
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10013', '1352353634', 'ad399178a994410ebbe21fa9f95c51e1', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10014', '135235363900000', '6e10815ba2e64398b18e9e697dfc9d17', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10015', '454056433529389056', 'dec3b806b8204dfbbe2f46c45f1b1ec2', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10016', '454056537858506752', '38c4607115a74024adf6262f3620575a', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10017', '454056597853831168', '44935797efde4f35a1d5a2d7e75817a7', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10018', '454056691885932544', '0c72e37fc3a3470584f5d86480859524', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10019', '454056875088936960', '872ef120e2d043a5b4112aa8257d4181', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10020', '454059519949930496', '618aa9a3fe1d4824a5ef12376f433372', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10021', '454059609443794944', '2c21bc10327d454c8513a93a65d5aa0e', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10022', '454059722518036480', 'e9d139085bb04cab8cd4f413a74dd3c4', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10023', '454059724459999232', '11378fe5da1e407ea01b8fb13df3223e', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10024', '454059724703268864', '7529c6325df648e6b203dac9c82848e3', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10025', '454059724912984064', '869f42f470434d6ca33faa2d81deda4c', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_0`.`test_order_0` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10026', '454059725156253696', '5921d2e76e294ea59bdd4d130d0415bd', '72352525', '????-1', '662525');

-- sharding_rep_1
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10001', '1352353631', '56a2517e94e4496db2aa31a42169c86b', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10003', '1352353635', '37c0b0712ae3466196a45e1309e4d2d9', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10004', '13523536347', '4a5f994046314ab28d7de694a0584b97', '12515151', '????-2', '325522');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10005', '1352353639', '38bb204fee504f8f85980ecb63c50dfe', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10006', '1352353639', '8292edc557604f189326997d1a35b17e', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10007', '454059724346753025', 'b57fbd635db7490ba245204893cffd08', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10008', '454059724569051137', '15f95be08b434260a5975959b3e4beae', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10009', '454059724820709377', 'cefe68aca76347e4a12a6312f24c070e', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10010', '454059725026230273', '7f1c14b9360a4df09b4c496dd47f96c8', '72352525', '????-1', '662525');
INSERT INTO `sharding_rep_1`.`test_order_1` (`id`, `order_id`, `order_no`, `goods_id`, `goods_name`, `user_id`) VALUES ('10011', '454059725244334081', '261fa84a961f48f7819c26b32a11ab38', '72352525', '????-1', '662525');

-- sharding_rep_slave
INSERT INTO `sharding_rep_slave`.`worker` (`id`, `name`, `money`) VALUES ('3', 'zhangsan', '10000313');