-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2021 at 08:42 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chatterbox`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientinfo`
--

CREATE TABLE `clientinfo` (
  `ClientId` int(11) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clientinfo`
--

INSERT INTO `clientinfo` (`ClientId`, `UserName`, `Password`) VALUES
(1001, 'bob', 'NR3XLPzYtTA3ZrHooe/ROA=='),
(1002, 'marley', 'NM+JgJRUo2pHd1gSo4qhuQ=='),
(1003, 'ali', 'LTxlK4RXUjwpEi1KH7q1Ww=='),
(1004, 'rhea', 'K7Yd4aY3lA9hTvjyxMdL1w==');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `Id` varchar(100) NOT NULL,
  `ClientId` int(11) NOT NULL,
  `Timestamp` date NOT NULL,
  `Message` varchar(500) NOT NULL,
  `Action` varchar(20) NOT NULL,
  `Source` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`Id`, `ClientId`, `Timestamp`, `Message`, `Action`, `Source`) VALUES
('9db122ed-096e-4ef6-b82a-9118bd9e3c73', 1003, '2021-12-12', 'U1uocaJUrCOaULshoVWnwJSXK2+yjMmB2PmwkDfYArvcOAlZBzthdgFbQi4VfacfjBwDTVAwkmSpLY/yBlDbWw==', 'Send', 'Client'),
('9db122ed-096e-4ef6-b82a-9118bd9e3c73', 1003, '2021-12-12', 'U1uocaJUrCOaULshoVWnwJSXK2+yjMmB2PmwkDfYArvcOAlZBzthdgFbQi4VfacfjBwDTVAwkmSpLY/yBlDbWw==', 'Received', 'Server'),
('844e09a1-66c8-404d-911d-9fb706f97e2b', 1001, '2021-12-12', 'CAfsj2joaPiU3rUFzLveyyqfzqs/RUwDMDeuk6wm+bFY8cdlGxPxIICzGKqxY1hnLvgddeZWm1jUoQNU0ZDKdQ==', 'Send', 'Client'),
('844e09a1-66c8-404d-911d-9fb706f97e2b', 1001, '2021-12-12', 'CAfsj2joaPiU3rUFzLveyyqfzqs/RUwDMDeuk6wm+bFY8cdlGxPxIICzGKqxY1hnLvgddeZWm1jUoQNU0ZDKdQ==', 'Received', 'Server'),
('844e09a1-66c8-404d-911d-9fb706f97e2b', 1001, '2021-12-12', 'ONuqF4O1wyiYFNXOJNgZjxIMeamxgFQliK/j1ocbuZd+PavE55fVGZP1OUBQxYQmKjqKmd55e4Agvfq23lFCHg==', 'Send', 'Client'),
('844e09a1-66c8-404d-911d-9fb706f97e2b', 1001, '2021-12-12', 'ONuqF4O1wyiYFNXOJNgZjxIMeamxgFQliK/j1ocbuZd+PavE55fVGZP1OUBQxYQmKjqKmd55e4Agvfq23lFCHg==', 'Received', 'Server'),
('6bd2ecd3-1f80-4861-be0a-0e13b5887f23', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8TQCMX/ZPSr13mGrkaTQ6XHLBkKNZNWQuf4Wip0hCIEY4HzMOf3+gU84u1MESUfCqg==', 'Send', 'Client'),
('6bd2ecd3-1f80-4861-be0a-0e13b5887f23', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8TQCMX/ZPSr13mGrkaTQ6XHLBkKNZNWQuf4Wip0hCIEY4HzMOf3+gU84u1MESUfCqg==', 'Received', 'Server'),
('173698df-9d22-4f23-a7c2-c12286182df6', 1003, '2021-12-12', 'V3dUYc5uRJa3BKwjGO6nriAqOXC5SIhNFG0z8teMJrLe/6iUrBUeHd6BC0fTizMRE1HktbnpwiDt4o8kafLR2A==', 'Send', 'Client'),
('173698df-9d22-4f23-a7c2-c12286182df6', 1003, '2021-12-12', 'V3dUYc5uRJa3BKwjGO6nriAqOXC5SIhNFG0z8teMJrLe/6iUrBUeHd6BC0fTizMRE1HktbnpwiDt4o8kafLR2A==', 'Received', 'Server'),
('6bd2ecd3-1f80-4861-be0a-0e13b5887f23', 1002, '2021-12-12', 'DkhcQYhe2xwvuq2A2vGEzsiX5r14XEDxTZNWFUIuDJwaI1mWZ8mmLVXUXdrfvaeuJxtSn1b4Khi3x8pKHnp3hQ==', 'Send', 'Client'),
('6bd2ecd3-1f80-4861-be0a-0e13b5887f23', 1002, '2021-12-12', 'DkhcQYhe2xwvuq2A2vGEzsiX5r14XEDxTZNWFUIuDJwaI1mWZ8mmLVXUXdrfvaeuJxtSn1b4Khi3x8pKHnp3hQ==', 'Received', 'Server'),
('173698df-9d22-4f23-a7c2-c12286182df6', 1003, '2021-12-12', 'RWhmrWQCZGIHML/D/WqH6gGGNfWrCMgVw5oB5mQyaktNs+gICV6nT+jDQlVmWLtRjhWO/TBG0t7okg6eMhIhpMBNdrdT3JriYL0/vGzcigw=', 'Send', 'Client'),
('173698df-9d22-4f23-a7c2-c12286182df6', 1003, '2021-12-12', 'RWhmrWQCZGIHML/D/WqH6gGGNfWrCMgVw5oB5mQyaktNs+gICV6nT+jDQlVmWLtRjhWO/TBG0t7okg6eMhIhpMBNdrdT3JriYL0/vGzcigw=', 'Received', 'Server'),
('2fa12d6b-6422-40b1-93a6-7d442e49a752', 1001, '2021-12-12', '5YSbddiiL6oHga2d3pAR9kbpB+2qN+PoYF0Xee4UJqbA2AXu/IsKKcwOe+GDAzzZPelzD5dEG8aQLE6edC7PMQ==', 'Send', 'Client'),
('2fa12d6b-6422-40b1-93a6-7d442e49a752', 1001, '2021-12-12', '5YSbddiiL6oHga2d3pAR9kbpB+2qN+PoYF0Xee4UJqbA2AXu/IsKKcwOe+GDAzzZPelzD5dEG8aQLE6edC7PMQ==', 'Received', 'Server'),
('535593e1-2d99-4e5d-8840-08f704c50298', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8ZsYVJz0dFE+795tKtaF+RHil4VWBQARSCmYDBWT143458ENdnMUt8+xHnaVtx9b0A==', 'Send', 'Client'),
('535593e1-2d99-4e5d-8840-08f704c50298', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8ZsYVJz0dFE+795tKtaF+RHil4VWBQARSCmYDBWT143458ENdnMUt8+xHnaVtx9b0A==', 'Received', 'Server'),
('c51673a6-429e-4629-8a3e-da77d2980d2a', 1003, '2021-12-12', 's89QXzJGJTzGcj8zeGm7WWdl/GwR0ZGWqURVg0e8NNf9hJ0GBS/tzf/R6gDp+y1xa2D7P7jhImbWyXHOfvUC8w==', 'Send', 'Client'),
('c51673a6-429e-4629-8a3e-da77d2980d2a', 1003, '2021-12-12', 's89QXzJGJTzGcj8zeGm7WWdl/GwR0ZGWqURVg0e8NNf9hJ0GBS/tzf/R6gDp+y1xa2D7P7jhImbWyXHOfvUC8w==', 'Received', 'Server'),
('ab4628cb-2d1c-45ac-ad1c-9d0c5af0fd62', 1004, '2021-12-12', 'nReDu+OMPUAIRNIFW/jYDlnaVDn3857l4mj3njwXhPQqceLFxQEx/ox0bRWdjI7H7jwNDJrM+64zDk1zuYGzXg==', 'Send', 'Client'),
('ab4628cb-2d1c-45ac-ad1c-9d0c5af0fd62', 1004, '2021-12-12', 'nReDu+OMPUAIRNIFW/jYDlnaVDn3857l4mj3njwXhPQqceLFxQEx/ox0bRWdjI7H7jwNDJrM+64zDk1zuYGzXg==', 'Received', 'Server'),
('73a62e4f-f838-420b-8c0a-c7615fa74626', 1003, '2021-12-12', 'Fm63fE4dgLn0L+e+tH8gDFb5+ICGS6xlgfjL/7lCzyP4o+xY7F7LAEUU2mGnwjp/jlV1uixZ0MDpEoJU8q1SlA==', 'Send', 'Client'),
('73a62e4f-f838-420b-8c0a-c7615fa74626', 1003, '2021-12-12', 'Fm63fE4dgLn0L+e+tH8gDFb5+ICGS6xlgfjL/7lCzyP4o+xY7F7LAEUU2mGnwjp/jlV1uixZ0MDpEoJU8q1SlA==', 'Received', 'Server'),
('1d3952a8-f108-47c6-8fc5-4b440bae7204', 1003, '2021-12-12', 'keedC+1QDlhJqFLpYxABmLQKIhWlQ6ZRIh5bJybBPL4KjsjgoVDnD6rva+u3VcXzAJvk3P9nxFC3xDEwqQ5GCw==', 'Send', 'Client'),
('1d3952a8-f108-47c6-8fc5-4b440bae7204', 1003, '2021-12-12', 'keedC+1QDlhJqFLpYxABmLQKIhWlQ6ZRIh5bJybBPL4KjsjgoVDnD6rva+u3VcXzAJvk3P9nxFC3xDEwqQ5GCw==', 'Received', 'Server'),
('8a14be24-15cc-4923-ad54-161d1c46ba09', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8SHTDAenC1pNS40WEYETR7hUorJWQ4e+OnTrWZ0nf52k0VRwzm31jyq5O4S1rhyxgQ==', 'Send', 'Client'),
('8a14be24-15cc-4923-ad54-161d1c46ba09', 1002, '2021-12-12', 'jLAye2rINvnC5FZ1Rvni8SHTDAenC1pNS40WEYETR7hUorJWQ4e+OnTrWZ0nf52k0VRwzm31jyq5O4S1rhyxgQ==', 'Received', 'Server'),
('73270e2d-c7cc-43d8-bfef-6e7ec8575d7b', 1004, '2021-12-12', 'xJ72VcvVEgH0BKtKrHLJVMAvG5RagzJbW6a+C6nL/bwBOrTuG2/Jewttt6ejaWthcKzBfWnNb4nFsQZx6xb0LQ==', 'Send', 'Client'),
('73270e2d-c7cc-43d8-bfef-6e7ec8575d7b', 1004, '2021-12-12', 'xJ72VcvVEgH0BKtKrHLJVMAvG5RagzJbW6a+C6nL/bwBOrTuG2/Jewttt6ejaWthcKzBfWnNb4nFsQZx6xb0LQ==', 'Received', 'Server');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientinfo`
--
ALTER TABLE `clientinfo`
  ADD PRIMARY KEY (`ClientId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
