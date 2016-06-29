--
-- Database Schema:  cae-schema 
-- Automatically generated sql script for the service Image Service, created by the CAE.
-- --------------------------------------------------------

--
-- Table structure for table images.
--
CREATE TABLE cae-schema.images (
  url varchar(255) ,
  id int ,
CONSTRAINT id_PK PRIMARY KEY (id)
);

--
-- Database Schema:  etst 
-- Automatically generated sql script for the service Image Service, created by the CAE.
-- --------------------------------------------------------

--
-- Table structure for table test.
--
CREATE TABLE `etst.test` (
  test int ,
CONSTRAINT test_PK PRIMARY KEY (test)
);

